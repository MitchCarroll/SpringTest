package com.asiwi.test.mitch.SpringTest;

import org.springframework.scheduling.annotation.Async;

public class MyDBBean {
	private String text;
	private String value;
	private MySQLAccess db; 
	
	public MyDBBean() {
		text = "DATABASE";
		db = new MySQLAccess();
		
	}
	

	@Async("taskExecutor")
	public void test() throws Exception {
		try {
			System.out.println(text+": Running Query");
			db.readDataBase();
			System.out.println(text+": Query Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
