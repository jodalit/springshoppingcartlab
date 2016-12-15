package com.alithya.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alithya.shoppingcart.service.ItemService;

@Controller
public class ShoppingOnlineHomeController {
	
	public static final String SESSION_BASKETSIZE = "basketsize";
	public static final String SESSION_ITEMS = "items";
	public static final String MODEL_NAME_WELCOME_INFO = "welcomeDeclaration";
	public static final String MODEL_NAME_WELCOME_TITLE = "welcomeTitle";
	public static final String REQUESTMAPPING_HOME = "/";
	public static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";
	public static final String WELCOME_TITLE ="Welcome to our online IT store !!!";
	public static final String WELCOME_INFO ="Welcome !!!";

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(REQUESTMAPPING_HOME) 
	public String getShoppingOnlineHome(ModelMap model, HttpServletRequest request){
		
		request.getSession().setAttribute(SESSION_ITEMS, itemService.getItemsList().values());
		model.addAttribute(MODEL_NAME_WELCOME_TITLE, WELCOME_TITLE);
		model.addAttribute(MODEL_NAME_WELCOME_INFO, WELCOME_INFO);
		
		return SHOPPING_ONLINE_HOME;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
}
