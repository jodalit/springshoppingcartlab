package com.formatiointerne.springmvc.pratiq1.controllers;

import javax.jws.WebParam.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShoppingOnlineSomeUtilities {
	public static final String CONNEXION ="Connexion to your on line store";
	public static final String BASKET ="Basket Breakdown";
	public static final String NEWUSER ="Choose your profil";
	
	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public String getConnexion(Model model){
		model.addAttribute("connexion", CONNEXION);
		return "shoppingonlineconnexion";
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public String sumitConnexion(Model model){
		int basketSize = 1;
		
		model.addAttribute("basketSize", basketSize);
		model.addAttribute("basket", BASKET);
		
		return "shoppingonlinehomeclient";
	}
	
	@RequestMapping(value="/newuser", method=RequestMethod.GET)
	public String getNewUser(Model model){
		model.addAttribute("newuser", NEWUSER);
		return "shoppingonlinenewuser";
	}
	
	@RequestMapping(value="/deconnexion", method=RequestMethod.GET)
	public String getDeconnexion(Model model){
		model.addAttribute("connexion", CONNEXION);
		return "redirect:/";
	}
}
