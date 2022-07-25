package com.learning.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tb_staff")
public class Staff{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String username;
	String fullName;
	String password;
	boolean allow;
	
	Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	Staff(int id, String username, String fullName, String password) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", username=" + username + ", fullName=" + fullName + ", password=" + password
				+", allow=" +isAllow()+ "]";
	}

	public boolean isAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
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
		Staff other = (Staff) obj;
		return id == other.id;
	}





	
}
