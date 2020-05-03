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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("vedio")
public class VedioController {
	@Value("${upload.locationvedios}")
	private String uploadfolder;
	@RequestMapping(value="/upload")
	public void uploadvedio(MultipartFile vedio) throws IllegalStateException, IOException
	{
		System.out.println(vedio.getOriginalFilename());
		vedio.transferTo(new File(uploadfolder+vedio.getOriginalFilename()));
	}

	@RequestMapping(value="/all")
public ResponseEntity<String[]> getallvedioslist()
    {
	File file=new File(uploadfolder);
	 String[] files=file.list();
	 return new ResponseEntity<String[]>(files, HttpStatus.OK);
	}

@RequestMapping(value="/download/{filename}")
public ResponseEntity<ByteArrayResource> downloadvedio(@PathVariable String filename) throws IOException
    {
	 Path path=Paths.get(uploadfolder+filename);
	byte[] bytes= Files.readAllBytes(path);
	ByteArrayResource bar=new ByteArrayResource(bytes);
	return new ResponseEntity<ByteArrayResource>(bar, HttpStatus.OK);
	}
}
