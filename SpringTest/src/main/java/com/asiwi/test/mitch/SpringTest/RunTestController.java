package com.asiwi.test.mitch.SpringTest;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAsync
public class RunTestController {
	private MyBean myBean;
	
	@RequestMapping(value = "/runTest", method = RequestMethod.GET)
	public String runTest(@RequestParam(value="name", required=false, defaultValue="DefaultValue") String name, Model model) {
		System.out.println("CONTROLLER: Initializing bean for async job...");
		myBean = new MyBean("WEB");
		System.out.println("CONTROLLER: Starting async job...");
		myBean.test();
		System.out.println("CONTROLLER: Async job complete.");
		model.addAttribute("name", name);
		System.out.println("CONTROLLER: Name attribute set to " + name + ".");
		return "runTest";
	}
}
