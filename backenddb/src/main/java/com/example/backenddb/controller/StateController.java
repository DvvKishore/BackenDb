package com.example.backenddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.backenddb.pojo.StatePojo;
import com.example.backenddb.service.StateService;

@RestController
@CrossOrigin
@RequestMapping("states")
public class StateController {
	@Autowired
	private StateService stateService;
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<StatePojo>> staesById(@PathVariable() Integer id)
	{
		List<StatePojo> states =stateService.staesById(id);
		ResponseEntity<List<StatePojo>> re =new ResponseEntity<>(states,HttpStatus.OK);
		return re;
	}

}
