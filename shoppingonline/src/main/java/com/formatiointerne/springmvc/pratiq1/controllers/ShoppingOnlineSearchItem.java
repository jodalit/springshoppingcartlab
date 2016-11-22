package com.formatiointerne.springmvc.pratiq1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShoppingOnlineSearchItem {
	public static final String RESULTSEARCHTITLE ="Search result";
	
	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.GET)
	public String getSearchItem(Model model){
		model.addAttribute("resultSearch", RESULTSEARCHTITLE);
		return "shoppingonlinesearchresult";
	}
	
	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.POST)
	public String getResultSearchItem(Model model){
		model.addAttribute("resultSearch", RESULTSEARCHTITLE);
		model.addAttribute("numberItem", "0");
		return "shoppingonlinesearchresult";
	}
}
