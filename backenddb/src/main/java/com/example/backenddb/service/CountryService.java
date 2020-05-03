package com.example.backenddb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backenddb.dao.CountryDao;
import com.example.backenddb.pojo.CountryPojo;

@Service
public class CountryService {
	@Autowired
	private CountryDao countrtDao;
	public List<CountryPojo> countrylist()
	{
		return countrtDao.findAll();
		
	}
}
