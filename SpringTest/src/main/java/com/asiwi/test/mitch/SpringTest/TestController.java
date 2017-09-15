package com.asiwi.test.mitch.SpringTest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAsync
public class TestController {
	@Autowired 
	private ApplicationContext context;
	
	@RequestMapping(value = "/runTest", method = RequestMethod.GET)
	public String runTest(@RequestParam(value="name", required=false, defaultValue="DefaultValue") String name, Model model) throws Exception {
		System.out.println("CONTROLLER: Initializing beans for async jobs...");
		MyBean myBean = (MyBean) context.getBean("WebBean");
		MyDBBean dbBean = (MyDBBean) context.getBean("DbBean");
		myBean.setValue(name);
		
		System.out.println("CONTROLLER: Starting async jobs...");
		myBean.test();
		dbBean.test();
		System.out.println("CONTROLLER: Async jobs start completed.");
		
		model.addAttribute("name", name);
		System.out.println("CONTROLLER: Name attribute set to " + name + ".");
		return "runTest";
	}
	
	@RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
	public String checkStatus(@RequestParam(value="name", required=false, defaultValue="DefaultValue") String name, Model model) throws Exception {
		System.out.println("STATUS: Initializing beans...");
		MyBean myBean = (MyBean) context.getBean("WebBean");
		MyDBBean dbBean = (MyDBBean) context.getBean("DbBean");
		System.out.println("STATUS: Beans initialized.");
		model.addAttribute("people", dbBean.getAll());
		model.addAttribute("isTesting", myBean.isTesting());
		return "checkStatus";
	}
	
	/*
	@RequestMapping(value = "/checkStatus", method = RequestMethod.POST)
	public String inputRecord(Model model) {
		System.out.println("FORM: Initializing beans...");
		MyDBBean dbBean = (MyDBBean) context.getBean("DbBean");
		System.out.println("FORM: Beans initialized.");

		model.addAttribute("db", dbBean);

		return "checkStatus";
	}
	*/
	
}
