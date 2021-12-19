package com.example.databasedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.databasedemo.entity.DBDUserOrderEntity;


@Repository
public interface DBDOrderDAO extends JpaRepository<DBDUserOrderEntity, String> {
	
	

}
