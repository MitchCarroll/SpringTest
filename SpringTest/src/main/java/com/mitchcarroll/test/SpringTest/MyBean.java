package com.mitchcarroll.test.SpringTest;

import org.springframework.scheduling.annotation.Async;

public class MyBean {
	private String text;
	private String value;
	private boolean testing;
	
	public MyBean(String t) {
		testing = false;
		if(t.equals(""))
			text = "BLANK";
		else
			text = t;
		value = "";
	}
	
	public MyBean() {
		testing = false;
		text = "TEST";
	}
	
	public void setValue(String s) {
		value = s;
	}
	
	public String getValue() {
		return value;
	}

	@Async("taskExecutor")
	public void test() {
		testing = true;
		try {
			System.out.println(text+": Starting thread timer with value: " + value + "...");
			Thread.sleep(20_000); //sleep 20 seconds
			System.out.println(text+": Thread timer done with value: " + value + ".");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			testing = false;
		}
	}
	
	public boolean isTesting() {
		return testing;
	}
	
}
