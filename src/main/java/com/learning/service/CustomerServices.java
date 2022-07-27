package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Approver;
import com.learning.entity.Customer;
import com.learning.exception.ForbiddenException;
import com.learning.jpa.CustomerRepository;
import com.learning.jpa.StaffRepository;

@Service
public class CustomerServices {


	@Autowired
	public CustomerRepository customerRepository;
	
	@Transactional//customer can create login
	public Customer createCustomer(Customer entity) {
		entity = customerRepository.save(entity);
		return entity;
	}

	@Transactional(readOnly=true)//customer can login
	public ResponseEntity<Customer> autenticate(Customer entity) {

		ExampleMatcher approverMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnorePaths("id,allow");
		
		Example<Customer> exemple = Example.of(entity,approverMatcher);
		
		boolean exists = customerRepository.exists(exemple);

		if (exists) {
			return ResponseEntity.ok().body(entity);
		}else {
			throw new ForbiddenException("ERROR 403 - Accesss DENIED. Wrong Username or Password. Click on forgot Password or Please contact support");
		}
	}
	
	

}
