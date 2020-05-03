package com.example.backenddb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backenddb.dao.NameCheckDao;
import com.example.backenddb.pojo.StudentPojo;

@Service
public class NameCheckService {
	@Autowired
	private NameCheckDao namecheckDao;
	
	public Boolean namevalidation(String name)
	{
		List<StudentPojo> stusents=namecheckDao.findByName(name);
		if(stusents!=null &stusents.size()>0)
		{
			return false;
		}
		return true;
	}

}
