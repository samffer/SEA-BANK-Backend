package com.learning.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Approver;
import com.learning.entity.Staff;
import com.learning.exception.ForbiddenException;
import com.learning.jpa.ApproverRepository;
import com.learning.jpa.StaffRepository;

@Service
public class ApproverServices {

	@Autowired
	private ApproverRepository AproverRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Transactional(readOnly=true)
	public ResponseEntity<Approver> autenticate(Approver entity) {

		ExampleMatcher approverMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnorePaths("id");
		
		Example<Approver> exemple = Example.of(entity,approverMatcher);
		
		boolean exists = AproverRepository.exists(exemple);

		if (exists) {
			return ResponseEntity.ok().body(entity);
		}else {
			throw new ForbiddenException("ERROR 403 - ACCESS DENIED. Username or Password wrong, Please contact support if you lost your credentials");
		}
	}

	@Transactional
	public ResponseEntity<Staff> createStaff(Staff entity) {
		ExampleMatcher staffMatcher = ExampleMatcher.matching()
				.withIgnoreNullValues()
				.withIgnorePaths("id")
				.withIgnorePaths("password")
				.withIgnorePaths("fullName")
				.withIgnorePaths("allow");
		
		
		Example<Staff> exemple = Example.of(entity,staffMatcher);
		
		boolean exists = staffRepository.exists(exemple);

		System.out.println(exists);//This print is for control
		
		if (exists) {
			throw new ForbiddenException("403 - (Forbidden) - Staff alredy exists");
		}else {
			entity = staffRepository.save(entity);

			return ResponseEntity.ok().body(entity);
		}
	}

	@Transactional(readOnly=true)
	public List<Staff> findAllPaged(/* PageRequest pagRequest */) {
		
		List<Staff> staffsPage = staffRepository.findAll(/* pagRequest */);
		return staffsPage;
	}

	
	@Transactional
	public Staff updateAllow(Staff staff, Boolean allow) {
		
		Staff temp = staffRepository.findById(staff.getId()).get();
		temp.setAllow(allow);
		staff=staffRepository.save(temp);
		System.out.println(staff.toString());
		return staff;
	}

	
	
	 
}
