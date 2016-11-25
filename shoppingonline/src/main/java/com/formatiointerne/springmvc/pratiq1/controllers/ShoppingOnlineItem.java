package com.formatiointerne.springmvc.pratiq1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShoppingOnlineItem {
	public static final String ADDNEWITEM ="Add New Item";
	
	@RequestMapping(value="/additem", method=RequestMethod.GET)
	public String getAddItem(Model model){
		model.addAttribute("addnewitem", ADDNEWITEM);
		return "shoppingonlinenewitem";
	}
	
}
