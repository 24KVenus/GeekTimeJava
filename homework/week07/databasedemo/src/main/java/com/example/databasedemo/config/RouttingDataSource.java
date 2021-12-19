package com.example.databasedemo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RouttingDataSource extends AbstractRoutingDataSource {


	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicSwitchDBSourceTool.get();
	}
}
