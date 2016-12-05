package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@Controller
public class ShoppingOnlineHome {
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!";
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/") 
	public String goShoppingOnlineHome(ModelMap model, HttpServletRequest request){
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		request.getSession().setAttribute("basketsize", 0);
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
		
		return "shoppingonlinehome";
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
}
