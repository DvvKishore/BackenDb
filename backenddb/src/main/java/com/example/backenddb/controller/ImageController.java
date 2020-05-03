package com.example.backenddb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin
@RequestMapping("images")
public class ImageController {
	
	@Value("${upload.location}")
	private String uploadFile;
	
    @RequestMapping(value="/upload",method=RequestMethod.POST)
	public void imageupload(MultipartFile image) throws IllegalStateException, IOException
	{
		System.out.println(image.getOriginalFilename());
		image.transferTo(new File(uploadFile+image.getOriginalFilename()));
	}
    
    @RequestMapping(value="/download/{filename}")
    public ResponseEntity<ByteArrayResource> imagedownload(@PathVariable String filename) throws IOException
    {
       Path path=Paths.get(uploadFile+filename);
     byte[] bytes= Files.readAllBytes(path);
     ByteArrayResource bar=new ByteArrayResource(bytes);
     
     return ResponseEntity.ok()
 			.header("content-disposition", "attachment;filename="+filename)
 			.body(bar);
    }
    
    
    @RequestMapping(value="/all")
    public ResponseEntity<String[]> getallnames()
    {
    	File file=new File(uploadFile);
    	String[] files=file.list();
    	return new ResponseEntity<String[]>(files, HttpStatus.OK);
    }
}
