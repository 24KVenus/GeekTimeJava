package com.example.databasedemo.config;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBDDataSourceConfig {
	
	@Bean(name = "db0")
	@Primary
    @ConfigurationProperties(prefix = "spring.datasource.db0")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource followDataSource() {
        return DataSourceBuilder.create().build();
    }
    
//    @Bean
//    public DataSource targetDataSource(@Qualifier("mainDataSource") DataSource mainDataSource, 
//    		@Qualifier("followDataSource") DataSource followDataSource) {
//    	Map<Object, Object> targetDataSource = new HashMap<>();
//    	targetDataSource.put(DBDDBSourceEnum.MAIN, mainDataSource);
//    	targetDataSource.put(DBDDBSourceEnum.FOLLOW, followDataSource);
//    	
//    	RouttingDataSource routtingDataSource = new RouttingDataSource();
//    	routtingDataSource.setTargetDataSources(targetDataSource);
//    	routtingDataSource.setDefaultTargetDataSource(mainDataSource);
//    	return routtingDataSource;
//    }
	
}
