package com.example.backenddb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backenddb.pojo.StudentPojo;
@Repository
public interface StudentDao extends JpaRepository<StudentPojo, Integer>{
	@Query(" from StudentPojo where name=?1")
	public abstract List<StudentPojo> getStudentName(String name);
	
	public abstract List<StudentPojo> findByName(String name);
	
	public abstract List<StudentPojo> findByAge(Integer age);
	

}
