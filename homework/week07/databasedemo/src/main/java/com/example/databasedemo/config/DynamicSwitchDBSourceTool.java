package com.example.databasedemo.config;

public class DynamicSwitchDBSourceTool {

	private static final ThreadLocal<DBDDBSourceEnum> CONTEXT_HAND = new ThreadLocal<>();
	
	public static void set(DBDDBSourceEnum dbSourceEnum) {
		CONTEXT_HAND.set(dbSourceEnum);
	}
	
	
	public static void mainDB() {
		set(DBDDBSourceEnum.MAIN);
	}
	
	
	public static void followDB() {
		set(DBDDBSourceEnum.FOLLOW);
	}
	
	public static void remove() {
		CONTEXT_HAND.remove();
	}
	
	public static DBDDBSourceEnum get() {
		return CONTEXT_HAND.get();
	}
	
}
