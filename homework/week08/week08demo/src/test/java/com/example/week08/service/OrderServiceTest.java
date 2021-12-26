package com.example.week08.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.week08.entity.OrderEntity;
import com.example.week08.tools.EntityGenerator;


@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	OrderService service;
	
	@Test
	public void addOrderTest() {
		
		
//		ArrayList<OrderEntity> list = generateOrdersArrayList(1000000);
//			
//		ExecutorService eService = Executors.newFixedThreadPool(500);
//		for (OrderEntity entity : list) {
//			eService.submit(() -> {
//				service.addOrder(entity);
//			});
//		}
//		eService.shutdown();
//		try {
//			while (!eService.awaitTermination(5, TimeUnit.MICROSECONDS));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	
	}
	
	@Test
	public void addAllTest() {
		ArrayList<OrderEntity> list = generateOrdersArrayList(10);
		int a = service.addAll(list);
		assertThat(a == 10);
	}
	
	
	@Test
	public void deleteOrderTest() {
//		BigInteger orderId = new BigInteger("681794373776027649");
//		service.deleteOrder(orderId);
	}
	
	
	@Test
	public void deleteOrderByUserIdTest() {
		
//		BigInteger userId = BigInteger.valueOf(54);
//		service.deleteOrderUserId(userId);
		
	}
	
	@Test
	public void updateOrderTest() {
		
//		BigInteger orderId = new BigInteger("681794370097623041");
//		Optional<OrderEntity> orderEntity = service.getOrderById(orderId);
//		if(orderEntity.isPresent()) {
//			OrderEntity order = orderEntity.get();
//			order.setStatus("1");
//			service.updaOrder(order);
//		}
	}
	
	
	@Test
	public void fineOrderTest() {
		
//		BigInteger orderId = new BigInteger("681794370097623041");
//		Optional<OrderEntity> orderEntity = service.getOrderById(orderId);
//		assertThat(orderEntity != null);
		
	}
	
	
	@Test
	public void fineOrderByUserIdTest() {
		
//		BigInteger userId = BigInteger.valueOf(23);
//		List<OrderEntity> list = service.listOrderByUserId(userId);
//		assertThat(list.size() > 0);
	}
	
	
	private ArrayList<OrderEntity> generateOrdersArrayList(int amount) {
		
		ArrayList<OrderEntity> list = new ArrayList<>(amount);
		
		for (int i = 0; i < amount; i++) {
			list.add(EntityGenerator.randomGegerateAOrder());
		}
		
		return list;
	} 
	

}
