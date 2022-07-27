package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Staff;
import com.learning.exception.ForbiddenException;
import com.learning.jpa.CustomerRepository;
import com.learning.jpa.StaffRepository;

@Service
public class StaffServices {

	@Autowired
	private StaffRepository staffRepository; 
	
	@Autowired
	private CustomerRepository customerRepository;

	@Transactional(readOnly=true)//Staff login
	public ResponseEntity<Staff> autenticate(Staff entity) {
		ExampleMatcher staffMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnorePaths("id").withIgnorePaths("allow").withIgnorePaths("fullName");
		Example<Staff> exemple = Example.of(entity,staffMatcher);
		
		boolean exists = staffRepository.exists(exemple);
		List<Staff> list = staffRepository.findAll(exemple);
		

		if (exists && list.get(0).isAllow()) {
			
			return ResponseEntity.ok().body(entity);
		}else {
			throw new ForbiddenException("ERROR 403 - Access DENIED. Wrong Username or Password Please contact your manager if you forgot your credentials");
		}
	}
	

	
	

}
