package com.alithya.shoppingcart.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alithya.shoppingcart.repository.ItemRepository;
import com.alithya.shoppingcart.service.ItemService;

@Controller
public class ShoppingOnlineHomeController {
	public static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!";

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/") 
	public String goShoppingOnlineHome(ModelMap model, HttpServletRequest request){
		
		request.getSession().setAttribute("items", itemService.itemsList().values());
		request.getSession().setAttribute("basketsize", 0);
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
		
		return SHOPPING_ONLINE_HOME;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
}
