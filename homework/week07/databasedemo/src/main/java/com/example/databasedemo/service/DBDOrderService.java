package com.example.databasedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.databasedemo.config.ReadDB;
import com.example.databasedemo.config.WriteDB;
import com.example.databasedemo.dao.DBDOrderDAO;
import com.example.databasedemo.entity.DBDUserOrderEntity;
import com.example.databasedemo.mapper.DBDOrderMapper;



@Service
public class DBDOrderService extends DBDBaseService {
	
	
	@Autowired
	DBDOrderDAO dao;
	
	public int addOrder(DBDUserOrderEntity order) {
		
		try {
			dao.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	@Autowired
	DBDOrderMapper mapper;
	
	@WriteDB
	public int add(DBDUserOrderEntity order) {
		mapper.insert(order);
		return 0;
	}
	
	@ReadDB
	public List<DBDUserOrderEntity> findAll() {
		return mapper.selectAll();
	}
	
}
