package com.example.databasedemo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.databasedemo.entity.DBDUserOrderEntity;

import tools.EntityGenerator;

@SpringBootTest

public class DBDServiceTest {
	
	@Autowired
	DBDOrderService service;
	
	@Test
	public void addOrderTest() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		// id = 1 createdate 2021-12-19 11:13:21.480
		// id = 31051 createdate 2021-12-19 11:20:29.961
//		for (int i = 0; i < 1000000; i++) {
//			DBDUserOrderEntity orderEntity = EntityGenerator.randomGegerateAOrder();
//			service.addOrder(orderEntity);
//		}
		
		// id = 71052  2021-12-19 11:47:36.157
		// id = 81051  2021-12-19 11:49:55.550
//		ArrayList<DBDUserOrderEntity> list = generateOrdersArrayList(10000);
//		System.out.println(formatter.format(new Date()));
//		for (DBDUserOrderEntity dbdUserOrderEntity : list) {
//			service.addOrder(dbdUserOrderEntity);		
//		}
//		System.out.println(formatter.format(new Date()));
		
		
		
		
		// id = 81084              id = 91083 
		// 2021-12-19 12:16:28.732 2021-12-19 12:16:57.840
		// 10线程
//		ArrayList<DBDUserOrderEntity> list = generateOrdersArrayList(10000);
//		ExecutorService eService = Executors.newFixedThreadPool(10);
//		System.out.println(formatter.format(new Date()));
//		for (DBDUserOrderEntity dbdUserOrderEntity : list) {
//			eService.submit(() -> {
//				service.addOrder(dbdUserOrderEntity);
//			});
//		}
//		eService.shutdown();
//		try {
//			while (!eService.awaitTermination(5, TimeUnit.MICROSECONDS));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		System.out.println(formatter.format(new Date()));
		
		
		// id = 131019                  id = 141018
		// 2021-12-19 17:07:24.205      2021-12-19 17:07:40.657
		// 线程20  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 30
		
		// id = 141019                  id = 151018
	    // 2021-12-19 17:10:04.377      2021-12-19 17:10:14.041
		// 线程40  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 50
		
		// id = 151019                  id = 161018
	    // 2021-12-19 17:12:54.342      2021-12-19 17:13:01.059
		// 线程90  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 100
		
		// id = 161019                  id = 171018
	    // 2021-12-19 17:16:04.482      2021-12-19 17:16:09.206
		// 线程490  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 500
		
		// 2021-12-19 17:19:43.170    2021-12-19 17:24:29.631
		// 线程490  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 500
		
		// 2021-12-19 17:42:19.131    2021-12-19 17:47:33.602  
		// 线程990  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 1000
		
		// 2021-12-19 21:23:25.684    2021-12-19 21:28:23.613
		//线程600  数据库连接池hikari  minimum-idle: 5 maximum-pool-size: 610
		ArrayList<DBDUserOrderEntity> list = generateOrdersArrayList(1000000);
		ExecutorService eService = Executors.newFixedThreadPool(600);
		System.out.println(formatter.format(new Date()));
		for (DBDUserOrderEntity dbdUserOrderEntity : list) {
			eService.submit(() -> {
				service.addOrder(dbdUserOrderEntity);
			});
		}
		eService.shutdown();
		try {
			while (!eService.awaitTermination(5, TimeUnit.MICROSECONDS));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(formatter.format(new Date()));
		
		
		assertThat(0);
	}
	
	private ArrayList<DBDUserOrderEntity> generateOrdersArrayList(int amount) {
		
		ArrayList<DBDUserOrderEntity> list = new ArrayList<>(amount);
		
		for (int i = 0; i < amount; i++) {
			list.add(EntityGenerator.randomGegerateAOrder());
		}
		
		return list;
	} 
	
	
	
	
}
