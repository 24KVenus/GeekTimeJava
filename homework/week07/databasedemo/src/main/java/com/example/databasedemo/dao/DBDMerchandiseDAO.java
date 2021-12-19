package com.example.databasedemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.databasedemo.entity.DBDMerchandiseEntity;


@Repository
public interface DBDMerchandiseDAO extends JpaRepository<DBDMerchandiseEntity, String> {

}
