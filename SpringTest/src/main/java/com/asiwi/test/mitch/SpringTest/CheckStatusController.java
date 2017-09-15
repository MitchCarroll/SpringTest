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
public class CheckStatusController {
	@Autowired 
	private ApplicationContext context;
	
	@RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
	public String runTest(@RequestParam(value="name", required=false, defaultValue="DefaultValue") String name, Model model) throws Exception {
		System.out.println("STATUS: Initializing beans...");
		MyBean myBean = (MyBean) context.getBean("WebBean");
		MyDBBean dbBean = (MyDBBean) context.getBean("DbBean");
		System.out.println("STATUS: Beans initialized.");
		model.addAttribute("people", dbBean.getAll());
		model.addAttribute("isTesting", myBean.isTesting());
		return "checkStatus";
	}
}
