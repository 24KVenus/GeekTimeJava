package com.example.databasedemo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_merchandise")
public class DBDOrderMerchandiseEntity extends DBDBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int orderId;
	private int merchandiseId;
	private String merchandiseName;
	private String merchandiseAvaterUri;
	private BigDecimal price;
	private int count;
	private int isDeleted;
	
	public int getId() {
		return id;
	}
	public int getOrderId() {
		return orderId;
	}
	public int getMerchandiseId() {
		return merchandiseId;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public String getMerchandiseAvaterUri() {
		return merchandiseAvaterUri;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public int getCount() {
		return count;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setMerchandiseId(int merchandiseId) {
		this.merchandiseId = merchandiseId;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public void setMerchandiseAvaterUri(String merchandiseAvaterUri) {
		this.merchandiseAvaterUri = merchandiseAvaterUri;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	

}
