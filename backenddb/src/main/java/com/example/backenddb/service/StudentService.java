package com.example.backenddb.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backenddb.dao.StudentDao;
import com.example.backenddb.pojo.StudentPojo;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentdao; 
	
 @Transactional	
 public void studentsave(StudentPojo student)
 {
	 studentdao.save(student);
 }
 @Transactional
 public void studentdelete(StudentPojo student)
 {
	 studentdao.delete(student);
 }
 @Transactional
 public void studentupdate(StudentPojo student)
 {
	 studentdao.save(student);
 }
 
 public StudentPojo getbyid(Integer id)
 {
	
	Optional<StudentPojo>option=studentdao.findById(id);
	return option.get();
	
 }

 public List<StudentPojo> getall(Integer pageno,Integer pagesize )
 {
	Page<StudentPojo> page= studentdao.findAll(new PageRequest(pageno,pagesize));
	 return page.getContent();
 
 }
 
 public List<StudentPojo> getStudentName(String name)
 {
	return studentdao.getStudentName(name);
	 
 }
	
	public  List<StudentPojo> findByName(String name)
	{
		return studentdao.findByName(name);
	}
	
	public  List<StudentPojo> findByAge(Integer age)
	{
		return studentdao.findByAge(age);
	}
	
	public List<StudentPojo> getallrecordsDownload()
	 {
		List<StudentPojo> page= studentdao.findAll();
		 return page;
	 
	 }
 
}
