package com.example.week08.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.week08.dao.OrderDAO;
import com.example.week08.entity.OrderEntity;

import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;




@Service
public class OrderService {
	
	@Autowired
	OrderDAO dao;
	
	public OrderEntity addOrder(OrderEntity order) {

		try {
			OrderEntity o = dao.save(order);
			if (o != null) {
				return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
	public int addAll(List<OrderEntity> orderList) {
    	List<OrderEntity> list = new ArrayList<OrderEntity>();
		for (OrderEntity order : orderList) {
			list.add(dao.save(order));
		}
		return list.size();
	}
	
	public void deleteOrder(BigInteger orderId) {
		dao.deleteById(orderId);
	}
	
	public void deleteOrderUserId(BigInteger userId)  {
		dao.deleteOrderByUserId(userId);
	}
	
	
	public OrderEntity updaOrder(OrderEntity order) {
		return dao.save(order);
	}
	
	public Optional<OrderEntity> getOrderById(BigInteger orderId) {
		return dao.findById(orderId);	
	}
	
	public List<OrderEntity>  listOrderByUserId(BigInteger userId) {
		return dao.listOrderByUserId(userId);	
	}
}
