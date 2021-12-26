package com.example.week08.tools;

import java.math.BigInteger;
import java.util.Random;

import com.example.week08.entity.OrderEntity;


public class EntityGenerator {
	
	public static OrderEntity randomGegerateAOrder() {
		OrderEntity orderEntity = new OrderEntity();
		
		Random r = new Random();
		orderEntity.setUserId(BigInteger.valueOf(r.nextInt(100)));
		orderEntity.setStatus("0");
				
		return orderEntity;
	}

}
