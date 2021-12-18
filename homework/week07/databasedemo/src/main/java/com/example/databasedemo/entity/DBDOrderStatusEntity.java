package com.example.databasedemo.entity;

public class DBDOrderStatusEntity extends DBDBaseEntity {
	
	private int id;
	private String statusCode;
	private String statusName;
	private int isDeleted;
	
	
	public int getId() {
		return id;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	

}
