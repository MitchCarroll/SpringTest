package com.asiwi.test.mitch.SpringTest;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

public class MyDBBean {
	private String text;
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
			db.writeResultSet();
			db.close();
			System.out.println(text+": Query Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<String> getAll() {
		try {
			db.readDataBase();
			return db.getNameList();
		} catch(Exception e) {
			return null;
		} finally {
			db.close();
		}		
	}

}
