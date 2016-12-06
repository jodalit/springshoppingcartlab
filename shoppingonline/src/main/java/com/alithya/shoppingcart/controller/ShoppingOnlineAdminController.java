package com.alithya.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingOnlineAdminController {
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String CLIENT ="New Client of the on line store";
		
	@RequestMapping(value="/client", method=RequestMethod.GET)
	public String getNewClient(ModelMap model){
		model.addAttribute("client", CLIENT);
		return SHOPPING_ONLINE_HOME_ADMIN;
	}

}
