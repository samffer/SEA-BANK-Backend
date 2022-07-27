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

import com.learning.entity.Approver;
import com.learning.entity.Staff;
import com.learning.service.ApproverServices;

@RestController
@CrossOrigin
@RequestMapping(value = ("/api/admin"))
public class ApproverController {

	@Autowired
	private ApproverServices service;
	
	
	@PostMapping(value = "/authenticate") //admin login
	public ResponseEntity<Approver> authenticate(@RequestBody Approver entity) {
		System.out.println(entity);
		ResponseEntity<Approver> response = service.autenticate(entity);
		System.out.println(response);
		return response;
	}
	
	@PostMapping(value = "/staff") //create staff
	public ResponseEntity<Staff> createStaff(@RequestBody Staff entity) {
		ResponseEntity<Staff> response = service.createStaff(entity);
		return response;
	}
	
	@GetMapping
	public ResponseEntity<List<Staff>> findAll(
	/*
	 * @RequestParam(value = "page", defaultValue = "0") Integer page,
	 * 
	 * @RequestParam(value = "linesPerPage", defaultValue = "10") Integer
	 * linesPerPage,
	 * 
	 * @RequestParam(value = "orderBy", defaultValue = "fullName") String orderBy,
	 * 
	 * @RequestParam(value = "direction", defaultValue = "ASC") String direction
	 */
			){
		
		//PageRequest pagRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		List<Staff> list = service.findAllPaged(/* pagRequest */);
		return ResponseEntity.ok().body(list);
		}
	
	@PutMapping(value = "{allow}")
	public ResponseEntity<Staff> updateAllow(@PathVariable Boolean allow, @RequestBody Staff staff){
		System.out.println(allow);
		staff = service.updateAllow(staff, allow);
		return ResponseEntity.ok().body(staff);
}
}
