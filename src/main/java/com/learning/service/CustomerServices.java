package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Account;
import com.learning.entity.Approver;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.exception.ForbiddenException;
import com.learning.jpa.AccountRepository;
import com.learning.jpa.CustomerRepository;
import com.learning.jpa.StaffRepository;

@Service
public class CustomerServices {

	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public AccountRepository accountRepository;

	@Transactional // customer can create login
	public Customer createCustomer(Customer entity) {
		ExampleMatcher staffMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnorePaths("id")
				.withIgnorePaths("password")
				.withIgnorePaths("fullName");

		Example<Customer> exemple = Example.of(entity, staffMatcher);

		boolean exists = customerRepository.exists(exemple);

		System.out.println(exists);// This print is for control

		if (exists) {
			throw new ForbiddenException("403 - (Forbidden) - User Name alredy in use, please try a different User Name");
		} else {
			entity = customerRepository.save(entity);

			return entity;
		}

	}

	@Transactional(readOnly = true) // customer can login
	public ResponseEntity<Customer> autenticate(Customer entity) {

		ExampleMatcher approverMatcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id,allow");

		Example<Customer> exemple = Example.of(entity, approverMatcher);

		boolean exists = customerRepository.exists(exemple);

		if (exists) {
			List<Customer> list = customerRepository.findAll(exemple);
			for (Customer customer : list) {
				if(customer.getUsername().equals(entity.getUsername())) {
					entity=customer;
				}
			}
			return ResponseEntity.ok().body(entity);
		} else {
			throw new ForbiddenException(
					"ERROR 403 - Accesss DENIED. Wrong Username or Password. Click on forgot Password or Please contact support");
		}
	}
	
	@Transactional
	public ResponseEntity<Account> createAccount(Account entity, Long customerId) {
		entity.setCustomerid(customerId);
		entity = accountRepository.save(entity);
		return ResponseEntity.ok().body(entity);
	}

	public ResponseEntity<List<Account>> getAllAccontsById(Long customerId) {
		Account entity = new Account();
		entity.setCustomerid(customerId);
		System.out.println(entity);
		ExampleMatcher accountMatcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("approved");
		Example<Account> exemple = Example.of(entity, accountMatcher);
		
		List<Account> list = accountRepository.findAll(exemple);
		System.err.println(list);
		return ResponseEntity.ok().body(list);
	}

}
