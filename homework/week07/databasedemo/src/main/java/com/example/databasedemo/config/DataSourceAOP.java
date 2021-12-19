package com.example.databasedemo.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAOP {

	@Pointcut("@annotation(com.example.databasedemo.config.ReadDB)")
	public void readPointcut() {}
	
	@Before("readPointcut()")
	public void readAdvise() {
		DynamicSwitchDBSourceTool.followDB();
	}
	
	@Pointcut("@annotation(com.example.databasedemo.config.WriteDB)")
	public void writePointcut() {}
	
	@Before("writePointcut()")
	public void writeAdvise() {
		DynamicSwitchDBSourceTool.mainDB();
	}
	
	
}
