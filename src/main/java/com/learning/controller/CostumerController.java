package com.learning.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.entity.Approver;
import com.learning.entity.Customer;
import com.learning.service.CustomerServices;

@RestController
@CrossOrigin
@RequestMapping(value="/api/custome")
public class CostumerController {

	@Autowired
	private CustomerServices service;
	
	@PostMapping(value="/register") //customer can create login
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer entity) {
		entity = service.createCustomer(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/register").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PostMapping(value = "/authenticate") //customer can login
	public ResponseEntity<Customer> authenticate(@RequestBody Customer entity) {
		System.out.println(entity);
		ResponseEntity<Customer> response = service.autenticate(entity);
		System.out.println(response);
		return response;
	}
	
	
	

}
