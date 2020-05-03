package com.example.backenddb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backenddb.pojo.StatePojo;
@Repository
public interface StateDao extends JpaRepository<StatePojo, Integer>{
	@Query("select s from StatePojo s join s.country c where c.id=?1")
	public List<StatePojo>	 getstatebyid(Integer id);

}
