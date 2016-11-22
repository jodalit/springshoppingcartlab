package com.formationinterne.projets.springmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formationinterne.projets.springmvc.datamodels.Item;
import com.formationinterne.projets.springmvc.services.shoppingServiceImplementation;

@Controller
public class HomeController {
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/>";
	
	@Autowired
	public shoppingServiceImplementation shoppingservice;
	
	
	@RequestMapping("/")
	public String homePage(Model model){
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
		
		return "shoppingcardHomePage";
	}
	
	@RequestMapping(value="searchresult", method=RequestMethod.GET)
	public String searchResult(){
		List<Item> 	l=	shoppingservice.findAllItems();
		l.forEach(System.out::println);
		
		return "shoppingcardSearchResultPage";
	}
	
	@RequestMapping(value="searchresultpost", method=RequestMethod.POST)
	public String searchResultPost(Model model){
		List<Item> 	l=	shoppingservice.findAllItems();
		l.forEach(System.out::println);
		
		model.addAttribute("listItem", l);
		return "shoppingcardSearchResultPage";
	}
}
