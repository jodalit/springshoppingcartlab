package com.alithya.shoppingcart.controller;

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
	public static final int USER_PROFILE = 1;

	public static final String REDIRECT = "redirect:/";

	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";

	public static final String ALLPEOPLE = "All people";

	@Autowired
	ServicePerson servicePerson;

	@RequestMapping(value = "/makeconnection", method = RequestMethod.POST)
	public String getshoppingonlinehomeadmin(@RequestParam("personConnectionName") String personConnectionName,
			@RequestParam("personPassword") String personPassword, HttpServletRequest request) {
		
		if (servicePerson.getPersonByConnexionPassword(personConnectionName, personPassword) != null) {
			request.getSession().setAttribute("connexionname",
					servicePerson.getPersonByConnexionPassword(personConnectionName, personPassword).getPersonConnectionName());

			if (servicePerson.getPersonByConnexionPassword(personConnectionName, personPassword).getProfile() == USER_PROFILE)
				return SHOPPING_ONLINE_HOME_ADMIN; 
			
		}

		return REDIRECT;
	}

	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}
	
}
