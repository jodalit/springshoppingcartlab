package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ServicePersonImplementation;

@Controller
public class ShoppingOnlineConnexion {
	
	@Autowired
	ServicePersonImplementation servicePerson;
	
	@RequestMapping(value="/makeconnexion", method=RequestMethod.POST)
	public String getshoppingonlinehomeclient(@RequestParam("personConnexion") String personConnexion, @RequestParam("personPassword") String personPassword, Model model, HttpServletRequest request){
		System.out.println("servicePerson.persons : \n"+ request.getSession().getAttribute("items"));
		System.out.println("request.getSession().getAttribute(items) : \n"+ request.getSession().getAttribute("items"));
		
		for (Person person : servicePerson.persons) {
			if (person.getConnexionname().trim().equals(personConnexion) && person.getPassword().trim().equals(personPassword)){
				//model.addAttribute("connexionname", person.getConnexionname().trim());
				request.getSession().setAttribute("connexionname", person.getConnexionname().trim());
				return "shoppingonlinehomeclient";
			}
		}
		
		return "redirect:/";
	}
}
