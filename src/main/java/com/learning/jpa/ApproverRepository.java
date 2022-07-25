package com.learning.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Approver;

@Repository
public interface ApproverRepository  extends JpaRepository<Approver, Long>{

}
