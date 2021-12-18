package com.example.databasedemo.entity;

public class DBDUserEntity extends DBDBaseEntity {

	

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
