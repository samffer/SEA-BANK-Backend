package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Staff;
import com.learning.service.StaffServices;

@RestController
@RequestMapping(value="/api/staff")
public class StaffController {

	@Autowired
	private StaffServices service;
	
	@CrossOrigin
	@PostMapping(value = "/authenticate") //Staff login
	public ResponseEntity<Staff> authenticate(@RequestBody Staff entity) {
		//System.err.println(entity);
		ResponseEntity<Staff> response = service.autenticate(entity);
		System.out.println(response);
		return response;
	}
	

}
