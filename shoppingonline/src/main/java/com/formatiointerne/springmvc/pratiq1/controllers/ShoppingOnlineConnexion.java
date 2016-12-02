package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ServicePerson;
import com.formatiointerne.springmvc.pratiq1.services.ServicePersonImplementation;

@Controller
public class ShoppingOnlineConnexion {
	public static final String ALLPEOPLE = "All people";
	
	@Autowired
	ServicePerson servicePerson;
	
	@RequestMapping(value="/makeconnexion", method=RequestMethod.POST)
	public String getshoppingonlinehomeclient(@RequestParam("personConnexion") String personConnexion, @RequestParam("personPassword") String personPassword, HttpServletRequest request){
		if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword)!=(null) ){
			request.getSession().setAttribute("connexionname", servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getConnexionname());
			
			if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getProfile() == 1)
				return "shoppingonlinehomeclient"; //admin
			else if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getProfile()==0) 
				return "shoppingonlinehomesuperadmin"; //super admin
			//else if (servicePerson.getPersonByConnexionPassword(personConnexion, personPassword).getProfile()==2) 
		}

		return "redirect:/";
	}
	
	@RequestMapping(value="/listpeople", method=RequestMethod.GET)
	public String getAllPeople(ModelMap model, HttpServletRequest request){
		model.addAttribute("allpeople", ALLPEOPLE);
		request.getSession().setAttribute("people", servicePerson.getPersons());
		
		return "shoppingonlinelistofallpeople";
	}

	public ServicePerson getServicePerson() {
		return servicePerson;
	}

	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}
		
}
