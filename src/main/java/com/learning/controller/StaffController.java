package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.entity.Staff;
import com.learning.service.StaffServices;

@CrossOrigin
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
	
	@CrossOrigin
	@GetMapping(value="/accounts/approve")
	public ResponseEntity<List<Account>> getAllAcountsToAprove() {
		ResponseEntity<List<Account>> entity = service.getAllAcountsToAprove();
		return entity;
	}
	
	@CrossOrigin
	@PutMapping(value="/accounts/approve{approve}")
	public ResponseEntity<Account> aproveAcounts(@PathVariable Boolean approve, @RequestBody Account account) {
		System.out.println(account);
		ResponseEntity<Account> entity = service.aproveAcounts(account,approve);
		return entity;
	}
	
	@CrossOrigin
	@GetMapping(value="/accounts/{accNo}")
	public ResponseEntity<Account> getAcountsById(@PathVariable Long accNo) {
		ResponseEntity<Account> entity = service.getAcountsById(accNo);
		return entity;
	}
	

}
