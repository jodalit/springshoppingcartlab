package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@Controller
public class ShoppingOnlineHome {
	public static final String WELCOMETITLE ="Welcome to our online IT store !!!";
	public static final String WELCOMEDECLARATION ="Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/> Welcome !!!<br/>";
	@Autowired
	private ItemServiceImplementation itemService;
	@RequestMapping("/") //shoppingonlinehome
	public String goShoppingOnlineHome(Model model, HttpServletRequest request){
		List<Item> items = itemService.items;
		request.getSession().setAttribute("items", items);
		model.addAttribute("welcomeTitle", WELCOMETITLE);
		model.addAttribute("welcomeDeclaration", WELCOMEDECLARATION);
				
		return "shoppingonlinehome";
	}
}
