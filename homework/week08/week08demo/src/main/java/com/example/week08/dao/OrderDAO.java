package com.example.week08.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.week08.entity.OrderEntity;


@Repository
public interface OrderDAO extends JpaRepository<OrderEntity, BigInteger>{

	@Query(nativeQuery = true, value = "delete from t_order where user_id = ?1")
	public void deleteOrderByUserId(BigInteger userId);
	
	@Query(nativeQuery = true, value = "select * from t_order where user_id = ?1")
	public List<OrderEntity> listOrderByUserId(BigInteger userId);
}
