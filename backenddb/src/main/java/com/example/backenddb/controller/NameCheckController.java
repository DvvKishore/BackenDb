package com.example.backenddb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backenddb.service.NameCheckService;

@Controller
@CrossOrigin
@RequestMapping("validation")
public class NameCheckController {
	@Autowired
	private NameCheckService namecheckservice;
	
	@RequestMapping(value="/check/{name}")
	public ResponseEntity<Boolean> namecheck(@PathVariable String name)
	{   
		
		Boolean result=namecheckservice.namevalidation(name);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
