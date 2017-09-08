package com.asiwi.test.mitch.SpringTest;

import org.springframework.scheduling.annotation.Async;

public class MyBean {
	private String text;
	private String value;
	
	public MyBean(String t) {
		if(t.equals(""))
			text = "BLANK";
		else
			text = t;
		value = "";
	}
	
	public MyBean() {
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
		try {
			System.out.println(text+": Starting thread timer with value: " + value + "...");
			Thread.sleep(5000);
			System.out.println(text+": Thread timer done with value: " + value + ".");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
