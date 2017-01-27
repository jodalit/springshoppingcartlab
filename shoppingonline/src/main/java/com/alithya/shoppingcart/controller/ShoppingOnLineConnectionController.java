package com.alithya.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alithya.shoppingcart.service.ServicePerson;

@Controller
public class ShoppingOnLineConnectionController {
	
	public static final String REQUESTMAPPING_MAKE_CONNECTION = "/makeconnection";
	public static final String MODEL_NAME_CONNECTION_NAME = "connectionname";
	public static final int USER_PROFILE = 1;
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String ALLPEOPLE = "All people";

	@Autowired
	ServicePerson servicePerson;

	@RequestMapping(value = REQUESTMAPPING_MAKE_CONNECTION, method = RequestMethod.GET)
	public String getShoppingOnLineHomeAdmin() {

		return SHOPPING_ONLINE_HOME_ADMIN;
	}
	
	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}
	
}
