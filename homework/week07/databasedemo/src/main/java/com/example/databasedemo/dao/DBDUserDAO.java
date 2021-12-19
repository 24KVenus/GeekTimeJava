package com.example.databasedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.databasedemo.entity.DBDUserEntity;


@Repository
public interface DBDUserDAO extends JpaRepository<DBDUserEntity, String> {

}
