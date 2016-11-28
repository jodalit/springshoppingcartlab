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
	public static final String ALLPEOPLE = "All people";
	
	@Autowired
	ServicePersonImplementation servicePerson;
	
	@RequestMapping(value="/makeconnexion", method=RequestMethod.POST)
	public String getshoppingonlinehomeclient(@RequestParam("personConnexion") String personConnexion, @RequestParam("personPassword") String personPassword, Model model, HttpServletRequest request){
		System.out.println("servicePerson.persons : \n"+ request.getSession().getAttribute("items"));
		System.out.println("request.getSession().getAttribute(items) : \n"+ request.getSession().getAttribute("items"));
		
		/*request.getSession().setAttribute("people", servicePerson.persons);
		System.out.println("people : \n"+ request.getSession().getAttribute("people"));*/
		
		for (Person person : servicePerson.persons) {
			if (person.getConnexionname().trim().equalsIgnoreCase(personConnexion) && person.getPassword().trim().equalsIgnoreCase(personPassword)){
				request.getSession().setAttribute("connexionname", person.getConnexionname().trim());
				if (person.getProfile()==1){ //admin
					return "shoppingonlinehomeclient";
				} else if (person.getProfile()==2){ //client
					
				}else if (person.getProfile()==0){ //super admin
					return "shoppingonlinehomesuperadmin";
				}
				
			}
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/listpeople", method=RequestMethod.GET)
	public String getAllItems(Model model, HttpServletRequest request){
		model.addAttribute("allpeople", ALLPEOPLE);
		request.getSession().setAttribute("people", servicePerson.persons);
		return "shoppingonlinelistofallpeople";
	}
}
