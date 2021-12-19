package com.example.databasedemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_register")
public class DBDUserEntity extends DBDBaseEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String phone;
	
	private String registerDate;
	
	
	public int getId() {
		return id;
	}
	public String getPhone() {
		return phone;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
}
