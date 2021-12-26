package com.example.week08.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_order")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger orderId;
	private BigInteger userId;
	private String status;
	
	
	public BigInteger getOrderId() {
	
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
	
		this.orderId = orderId;
	}
	public BigInteger getUserId() {
	
		return userId;
	}
	public void setUserId(BigInteger userId) {
	
		this.userId = userId;
	}
	public String getStatus() {
	
		return status;
	}
	public void setStatus(String status) {
	
		this.status = status;
	}
	
	
}
