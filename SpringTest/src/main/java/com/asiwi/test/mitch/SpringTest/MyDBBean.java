package com.asiwi.test.mitch.SpringTest;

import java.util.ArrayList;
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
			System.out.println(text+": Query Done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	public List<String> getAll() {
		List<String> ret = new ArrayList<String>();
		try {
			db.readDataBase();
			ret = db.getNameList();
		} catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();
		} finally {
			db.close();
		}
		return ret;
	}

/*	
	public void insert(String lname, String fname, String mname,  String dob, String sex) {
		db.insert(lname,fname,mname,dob,sex);
		db.close();
	}
*/
	
}
