package com.example.databasedemo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.databasedemo.dao.DBDOrderDAO;
import com.example.databasedemo.entity.DBDUserOrderEntity;

public class DBDOrderService extends DBDBaseService {
	
	
	@Autowired
	DBDOrderDAO dao;
	
	public int addOrder(DBDUserOrderEntity order) {
		
	
		return 0;
	}

}
