package com.example.backenddb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backenddb.pojo.StudentPojo;
@Repository
public interface NameCheckDao extends JpaRepository<StudentPojo, Integer>{
	@Query("select s.name from StudentPojo s where s.name=?1")
	public abstract List<StudentPojo> findByName(String name);

}
