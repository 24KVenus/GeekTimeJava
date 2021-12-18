package com.example.databasedemo.entity;

public class DBDUserOrderEntity extends DBDBaseEntity {

	private int id;
	private int userId;
	private int addressId;
	private String orderStatus;
	private String createDate;
	private String updateDate;
	private int isDeleted;
	
	
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public int getAddressId() {
		return addressId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	
	
}
