package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@Controller
public class ShoppingOnlineHome {
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/>";
	@Autowired
	private ItemServiceImplementation itemService;
	//@Autowired
	//private ShoppingServiceImplementation shoppingService;
	
	@RequestMapping("/") //shoppingonlinehome
	public String goShoppingOnlineHome(Model model, HttpServletRequest request){
	
		//Collection<Item> items = itemService.items.values();
		//Collections.list(Collections.enumeration(itemService.items.values()));
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.items.values())));
		request.getSession().setAttribute("basketsize", 0);
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
				
		return "shoppingonlinehome";
	}
}
