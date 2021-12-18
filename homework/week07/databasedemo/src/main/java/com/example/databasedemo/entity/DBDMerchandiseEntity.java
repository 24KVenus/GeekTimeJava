package com.example.databasedemo.entity;

import java.math.BigDecimal;

public class DBDMerchandiseEntity extends DBDBaseEntity {

	
	private int id;
	private int shopId;
	private String bandName;
	private String merchandiseName;
	private BigDecimal price;
	private String avater;
	private String describe;
	private String createDate;
	private String updateDate;
	private int isDeleted;
	
	public int getId() {
		return id;
	}
	public int getShopId() {
		return shopId;
	}
	public String getBandName() {
		return bandName;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getAvater() {
		return avater;
	}
	public String getDescribe() {
		return describe;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setAvater(String avater) {
		this.avater = avater;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
