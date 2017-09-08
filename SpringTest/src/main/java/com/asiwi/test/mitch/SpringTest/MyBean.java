package com.asiwi.test.mitch.SpringTest;

import org.springframework.scheduling.annotation.Async;

public class MyBean {
	private String text;
	public MyBean(String t) {
		if(t.equals(""))
			text = "BLANK";
		else
			text = t;
	}
	
	public MyBean() {
		text = "TEST";
	}

	@Async("taskExecutor")
	public void test() {
		try {
			System.out.println(text+": Starting thread timer...");
			Thread.sleep(5000);
			System.out.println(text+": Thread timer done.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
