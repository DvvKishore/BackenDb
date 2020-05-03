package com.example.backenddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backenddb.pojo.CountryPojo;
import com.example.backenddb.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("country")
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	 @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CountryPojo>> countryList()
	{
		List<CountryPojo> countries=countryService.countrylist();
		ResponseEntity<List<CountryPojo>> re=new ResponseEntity<>(countries,HttpStatus.OK);
		return re;
	}

}
