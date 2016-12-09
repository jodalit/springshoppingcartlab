package com.alithya.shoppingcart.controller;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ItemServiceImplementation;
import com.alithya.shoppingcart.service.ShoppingServiceImplementation;
import com.alithya.shoppingcart.service.shoppingService;

@Controller
public class ShoppingOnlineSomeUtilitiesController {
	public static final String SHOPPING_ONLINE_BASKET_RECEIVED = "shoppingonlinebasketreceived";
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_SEARCH_RESULT = "shoppingonlinesearchresult";
	public static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";
	public static final String SHOPPING_ONLINE_NEWUSER = "shoppingonlinenewuser";
	public static final String SHOPPING_ONLINE_BASKET = "shoppingonlinebasket";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String SHOPPING_ONLINE_CONNEXION = "shoppingonlineconnexion";
	public static final String CONNEXION = "Connexion to your on line store";
	public static final String BASKET = "Basket Breakdown";
	public static final String NEWUSER = "Choose your profil";
	
	@Autowired
	private shoppingService shoppingService;

	@Autowired
	private ItemService itemService;
	

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public String getConnexion(ModelMap model, HttpServletRequest request) {
		model.addAttribute("connexion", CONNEXION);
		return SHOPPING_ONLINE_CONNEXION;
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String sumitConnexion(ModelMap model) {
		int basketSize = 1;
		model.addAttribute("basketSize", basketSize);
		model.addAttribute("basket", BASKET);

		return SHOPPING_ONLINE_HOME_ADMIN;
	}
	
	@RequestMapping(value = "/connect", method = RequestMethod.GET)
	public String getShoppingOnlineHomeAdmin(ModelMap model, HttpServletRequest request) {
		model.addAttribute("connexion", CONNEXION);
		return SHOPPING_ONLINE_HOME_ADMIN;
	}

	@RequestMapping(value = "/showbasket", method = RequestMethod.GET)
	public String getBasket() {
		return SHOPPING_ONLINE_BASKET;
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String getNewUser(ModelMap model) {
		model.addAttribute("newuser", NEWUSER);
		return SHOPPING_ONLINE_NEWUSER;
	}

	@RequestMapping("/addtobasket/{itemId}")
	public String addToBasket(@PathVariable Long itemId, HttpServletRequest request) {
		infoBasket(itemId, request);
		return SHOPPING_ONLINE_HOME;
	}
	
	@RequestMapping("/addtobasketfromresultsearch/{itemId}")
	public String addToBasketFromResultSearch(@PathVariable Long itemId, HttpServletRequest request) {
		infoBasket(itemId, request);
		return SHOPPING_ONLINE_SEARCH_RESULT;
	}
	
	@RequestMapping("/payitems")
	public String payItems(HttpServletRequest request) {
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		
		if (basket==null)
			return REDIRECT;
		
		if (!shoppingService.removeItemsToBasket())
			return REDIRECT;
		
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0);
		
		return SHOPPING_ONLINE_BASKET_RECEIVED;
	}
	
	@RequestMapping("/removefrombasket/{itemId}")
	public String removeItemFromBasket(@PathVariable Long itemId, HttpServletRequest request) {
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		
		if (basket==null)
			return REDIRECT;
		
		if (!shoppingService.removeItemToBasket(itemId))
			return REDIRECT;
		
		Iterator<Item> iterat = basket.iterator(); 
		while (iterat.hasNext()) {
			Item item = (Item) iterat.next();
			if (item.getItemId().equals(itemId)){
				basket.remove(item);
				break;
			}
		}
		
		shoppingService.removeItemToBasket(itemId);
			
		request.getSession().setAttribute("basket", basket);
		request.getSession().setAttribute("basketsize",basket.size());	
		request.getSession().setAttribute("baskettotal", shoppingService.getTotalBasket());
	
		return SHOPPING_ONLINE_BASKET;
	}
	
	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String getDeconnexion(ModelMap model, HttpServletRequest request, WebRequest webrequest, SessionStatus sessionstatus) {
		
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0);
		request.getSession().setAttribute("connexionname", null);
		request.getSession().setAttribute("itemsforname", null);
		request.getSession().setAttribute("items", null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("people", null);
		
		
		sessionstatus.setComplete();
		webrequest.removeAttribute("basketsize", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("baskettotal", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("connexionname", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("itemsforname", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("items", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("basket", WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute("people", WebRequest.SCOPE_SESSION);
		
		return REDIRECT;
	}
	
	public void infoBasket(Long itemId, HttpServletRequest request){
		if (shoppingService.addItemToBasket(itemId)){
			request.getSession().setAttribute("basket", shoppingService.getBasket());
			request.getSession().setAttribute("basketsize", shoppingService.getBasket().size());
			request.getSession().setAttribute("baskettotal", shoppingService.getTotalBasket());
		}
	}

	public void setShoppingService(shoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	
}
