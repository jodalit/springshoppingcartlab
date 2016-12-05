package com.formatiointerne.springmvc.pratiq1.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingOnlineClient {
	public static final String CLIENT ="New Client of the on line store";
		
	@RequestMapping(value="/client", method=RequestMethod.GET)
	public String getNewClient(Model model){
		model.addAttribute("client", CLIENT);
		return "shoppingonlinenewclient";
	}

}
