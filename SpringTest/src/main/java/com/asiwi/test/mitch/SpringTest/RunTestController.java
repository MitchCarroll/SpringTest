package com.asiwi.test.mitch.SpringTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAsync
public class RunTestController {
	@Autowired 
	private ApplicationContext context;
	
	@RequestMapping(value = "/runTest", method = RequestMethod.GET)
	public String runTest(@RequestParam(value="name", required=false, defaultValue="DefaultValue") String name, Model model) throws Exception {
		System.out.println("CONTROLLER: Initializing bean for async job...");
		MyBean myBean = (MyBean) context.getBean("WebBean");
		MyDBBean dbBean = (MyDBBean) context.getBean("DbBean");
		myBean.setValue(name);
		
		System.out.println("CONTROLLER: Starting async jobs...");
		myBean.test();
		dbBean.test();
		System.out.println("CONTROLLER: Async jobs complete.");
		
		model.addAttribute("name", name);
		System.out.println("CONTROLLER: Name attribute set to " + name + ".");
		return "runTest";
	}
}
