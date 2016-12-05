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
	public static final String ALLPEOPLE = "All people";

	@Autowired
	ServicePerson servicePerson;

	@RequestMapping(value = "/makeconnexion", method = RequestMethod.POST)
	public String getshoppingonlinehomeadmin(@RequestParam("personConnexion") String personConnexion,
			@RequestParam("personPassword") String personPassword, HttpServletRequest request) {
		if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword) != (null)) {
			request.getSession().setAttribute("connexionname",
					servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getConnexionname());

			if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getProfile() == 1)
				return "shoppingonlinehomeadmin"; // admin
			// TODO unfinished feature please refer to JIRA task xyz
			else if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getProfile() == 0)
				return "shoppingonlinehomesuperadmin"; // super admin
		}

		return "redirect:/";
	}

	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}

	// TODO This feature will be enabled during Spring Security exercise
	/*
	 * @RequestMapping(value="/listpeople", method=RequestMethod.GET) public
	 * String getAllPeople(ModelMap model, HttpServletRequest request){
	 * model.addAttribute("allpeople", ALLPEOPLE);
	 * request.getSession().setAttribute("people", servicePerson.getPersons());
	 * 
	 * return "shoppingonlinelistofallpeople"; }
	 */

}
