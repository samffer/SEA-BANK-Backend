package com.learning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Account;
import com.learning.entity.Staff;
import com.learning.exception.ForbiddenException;
import com.learning.jpa.AccountRepository;
import com.learning.jpa.CustomerRepository;
import com.learning.jpa.StaffRepository;

@Service
public class StaffServices {

	@Autowired
	private StaffRepository staffRepository; 
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;

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

	@Transactional(readOnly=true) // staff get all accounts to approve
	public ResponseEntity<List<Account>> getAllAcountsToAprove() {
		
		Account entity = new Account();
		entity.setApproved(false);
		
		List<Account> list = new ArrayList<Account>();
		accountRepository.findAll().forEach(a->{
			if(!a.isApproved()) {
				list.add(a);
			}
		});
		return ResponseEntity.ok().body(list);
	}
	
	@Transactional
	public ResponseEntity<Account> aproveAcounts(Account account, Boolean approve) {
		account.setApproved(approve);
		account = accountRepository.save(account);
		System.out.println(account);
		return ResponseEntity.ok().body(account);
	}

	@Transactional(readOnly=true)
	public ResponseEntity<Account> getAcountsById(Long accNo) {
		Account entity = accountRepository.findById(accNo).orElseThrow(()-> new ForbiddenException("Account not found!"));
		
		return ResponseEntity.ok().body(entity);
	}
	

	
	

}
