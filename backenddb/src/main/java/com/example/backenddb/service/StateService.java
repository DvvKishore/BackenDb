package com.example.backenddb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backenddb.dao.StateDao;
import com.example.backenddb.pojo.StatePojo;

@Service
public class StateService {
	@Autowired
private StateDao statedao;
	
	public List<StatePojo> staesById(Integer id)
	{
		return statedao.getstatebyid(id);
	}
	

}
