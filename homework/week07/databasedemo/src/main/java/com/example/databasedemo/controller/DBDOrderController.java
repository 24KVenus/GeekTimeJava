package com.example.databasedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.databasedemo.entity.DBDUserOrderEntity;
import com.example.databasedemo.service.DBDOrderService;

@RestController
public class DBDOrderController extends DBDBaseController {
	
	
	@Autowired
	DBDOrderService orderService;
	
	
	public void addOrder(DBDUserOrderEntity order) {
		
		
		
	
	}
	
	

}
