package com.formatiointerne.springmvc.pratiq1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingOnlineHome {
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/>";
	
	@RequestMapping("/") //shoppingonlinehome
	public String goShoppingOnlineHome(Model model){
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
				
		return "shoppingonlinehome";
	}
}
