package com.learning.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
