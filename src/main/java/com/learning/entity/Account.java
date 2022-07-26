package com.learning.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_account")
public class Account {
	 
	public Account() {
	}
	
	
	public Account(Long id, String type, Double balance, boolean approved,Long customerid) {
		this.id = id;
		this.type = type;
		this.balance = balance;
		this.approved=approved;
		this.customerid = customerid;
		
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String  type;
	Double balance;
	boolean approved;
	Long customerid;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}





	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", balance=" + balance + ", approved=" + approved + "Customer id = "+customerid+"]";
	}


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	public Long getCustomerid() {
		return customerid;
	}


	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	
	

}
