package com.alithya.shoppingcart.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alithya.shoppingcart.service.ServicePerson;

@Controller
public class ShoppingOnlineConnexionController {
	
	public static final String REQUESTMAPPING_MAKE_CONNECTION = "/makeconnection";
	public static final String MODEL_NAME_CONNECTION_NAME = "connectionname";
	public static final int USER_PROFILE = 1;
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String ALLPEOPLE = "All people";

	@Autowired
	ServicePerson servicePerson;

	@RequestMapping(value = REQUESTMAPPING_MAKE_CONNECTION, method = RequestMethod.POST)
	public String getShoppingOnLineHomeAdmin(@RequestParam("personConnectionName") String personConnectionName,
			@RequestParam("personPassword") String personPassword, HttpServletRequest request) {
		
		if (servicePerson.getPersonByConnexionNamePassword(personConnectionName, personPassword)) {
			
			request.getSession().setAttribute(MODEL_NAME_CONNECTION_NAME, personConnectionName);
			if (servicePerson.getProfile() == USER_PROFILE)
				return SHOPPING_ONLINE_HOME_ADMIN; 
			
		}

		return REDIRECT;
	}

	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}
	
}
