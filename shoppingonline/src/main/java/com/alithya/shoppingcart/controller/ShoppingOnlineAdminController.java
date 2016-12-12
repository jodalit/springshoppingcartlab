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
	
	public static final String REQUESTMAPPING_ADMIN = "/admin";
	public static final String MODEL_NAME_ADMIN = "admin";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String ADMINISTRATION ="You're Administrator !!!";
		
	@RequestMapping(value=REQUESTMAPPING_ADMIN, method=RequestMethod.GET)
	public String getNewClient(ModelMap model){
		
		model.addAttribute(MODEL_NAME_ADMIN, ADMINISTRATION);
		
		return SHOPPING_ONLINE_HOME_ADMIN;
	}

}
