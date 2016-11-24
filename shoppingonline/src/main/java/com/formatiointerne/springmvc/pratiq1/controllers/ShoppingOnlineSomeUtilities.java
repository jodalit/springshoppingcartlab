package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@Controller
public class ShoppingOnlineSomeUtilities {
	public static final String CONNEXION ="Connexion to your on line store";
	public static final String BASKET ="Basket Breakdown";
	public static final String NEWUSER ="Choose your profil";
	
	@Autowired
	private ShoppingServiceImplementation shoppingServiceImplementation;
	
	@Autowired
	private ItemServiceImplementation itemService;
	
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
	
	@RequestMapping("/addtobasket")
	public String addToBasket(@RequestParam("itemId") Long itemId, HttpServletRequest request){
		List<Item> items = (List<Item>)request.getSession().getAttribute("items");
		
		for (Item item : items){//(List<Item>) request.getSession().getAttribute("items")) {
			if (item.getItemId()==itemId){
				shoppingServiceImplementation.addItemToBasket(item);
				shoppingServiceImplementation.removeItemOnItemsListOfClient(item, items);
				request.getSession().setAttribute("items", items);
				request.getSession().setAttribute("basket", shoppingServiceImplementation.basket);
				System.out.println("request.getContextPath() : "+ request.getContextPath());
			}
		}
		
		return "shoppingonlinehomeclient";
	}
	
	@RequestMapping(value="/deconnexion", method=RequestMethod.GET)
	public String getDeconnexion(Model model){
		model.addAttribute("connexionname", null);
		return "redirect:/";
	}
}
