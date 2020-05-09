package com.example.backenddb.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backenddb.pojo.StudentPojo;
import com.example.backenddb.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method=RequestMethod.POST)
	 public ResponseEntity studentsave(@RequestBody StudentPojo student)
	 {
		studentService.studentsave(student);
		ResponseEntity re=new ResponseEntity(HttpStatus.CREATED);
		return re;
	 }
	@RequestMapping(method=RequestMethod.DELETE)
	 public ResponseEntity studentdelete(@RequestBody StudentPojo student)
	 {
		studentService.studentdelete(student);
		ResponseEntity re=new ResponseEntity(HttpStatus.OK);
		return re;
	 }
	@RequestMapping(method=RequestMethod.PUT)
	 public ResponseEntity studentupdate(@RequestBody StudentPojo student)
	 {
		studentService.studentupdate(student);
		ResponseEntity re=new ResponseEntity(HttpStatus.OK);
		return re;
	 }
	@RequestMapping(value= "/{id}",method=RequestMethod.GET)
	 public ResponseEntity<StudentPojo> getbyid(@PathVariable()Integer id)
	 {
		StudentPojo std=studentService.getbyid(id);
		ResponseEntity<StudentPojo>  re=new ResponseEntity<>(std,HttpStatus.OK) ;
		return re;
	 }
	@RequestMapping(value="/{pageno}/{pagesize}",method=RequestMethod.GET)
	 public ResponseEntity<List<StudentPojo>>  getall(@PathVariable("pageno")Integer pageno,@PathVariable("pagesize")Integer pagesize)
	 {
		List<StudentPojo> listofstudents=studentService.getall(pageno,pagesize);
		ResponseEntity<List<StudentPojo>>  re=new ResponseEntity<>(listofstudents,HttpStatus.OK) ;
		return re;
	 }
	
	@RequestMapping(value="/getStudentName")
	public ResponseEntity<List<StudentPojo>> getStudentName(String name)
	 {
		List<StudentPojo> listofstudents=studentService.getStudentName(name);
		ResponseEntity<List<StudentPojo>>  re=new ResponseEntity<>(listofstudents,HttpStatus.OK) ;
		return re;
	 }
	@RequestMapping(value="/findByName")
		public  ResponseEntity<List<StudentPojo>> findByName(String name)
		{
			List<StudentPojo> listofstudents=studentService.findByName(name);
			ResponseEntity<List<StudentPojo>>  re=new ResponseEntity<>(listofstudents,HttpStatus.OK) ;
			return re;
		}
	@RequestMapping(value="/findByAge")
		public  ResponseEntity<List<StudentPojo>> findByAge(Integer age)
		{

			List<StudentPojo> listofstudents=studentService.findByAge(age);
			ResponseEntity<List<StudentPojo>>  re=new ResponseEntity<>(listofstudents,HttpStatus.OK) ;
			return re;
		}
	@RequestMapping(value="upload",method=RequestMethod.POST)	
	public void fileUpload(MultipartFile file) throws IllegalStateException, IOException
	{
		System.out.println(file.getOriginalFilename());
		String filename=file.getOriginalFilename();
		File rfile= new File("F:\\FileUploads\\"+filename);
		file.transferTo(rfile);
		
		FileReader fileread=new FileReader(rfile);
		BufferedReader br=new BufferedReader(fileread);
		
		String line=br.readLine();
		
		while(line!=null)
		{
			String[] values=line.split(",");
			if(values.length==4)
			{
			StudentPojo student=new StudentPojo();
			student.setAge(Integer.parseInt(values[0]));
			student.setName(values[1]);
			student.setPhoneno(values[2]);
			student.setQual(values[3]);
			line=br.readLine();
			studentService.studentsave(student);
		}
		
		}
	}
	@RequestMapping(value="download",method=RequestMethod.GET)	
	public ResponseEntity<ByteArrayResource> filedownload() throws IOException
	{
		List<StudentPojo> students=studentService.getallrecordsDownload();
		
			FileWriter fr=new FileWriter("F:\\FileUploads\\studentsdownload");
			BufferedWriter br=new BufferedWriter(fr);
			for (StudentPojo studentPojo : students) {
			br.newLine();
			br.write(studentPojo.getId()+","+studentPojo.getName()+","+studentPojo.getAge()+","+studentPojo.getPhoneno()+","+studentPojo.getQual());
			
			}
			br.close();
			fr.close();
			
			
			Path path=Paths.get("F:\\FileUploads\\studentsdownload");
			byte[] bytes=Files.readAllBytes(path);
			ByteArrayResource bar=new ByteArrayResource(bytes);
			return ResponseEntity.ok()
					.header("content-disposition", "attachment;filename=abc.txt")
					.body(bar);
				
			
			
			
			/*res.setHeader("Content-Disposition", "attachement;filename="+filename);
			File f=new File("F:\\FileUploads\\"+filename);
			FileInputStream fis=new FileInputStream(f);
		    PrintWriter pw= res.getWriter();
			int bytes=fis.read();
			
			while(bytes==-1)
			{
				pw.write(bytes);
				bytes=fis.read();
				
			}*/
			
		
	}
	
	@GetMapping(value = "/v1/data")
	public String sayHelloWorld() {
	 return "Hello Veera How are You!!!!";
	}

}
