package com.formatiointerne.springmvc.pratiq1.controllers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.shoppingService;

@Controller
public class ShoppingOnlineSomeUtilities {
	public static final String CONNEXION = "Connexion to your on line store";
	public static final String BASKET = "Basket Breakdown";
	public static final String NEWUSER = "Choose your profil";
	
	@Autowired
	private shoppingService shoppingService;

	@Autowired
	private ItemService itemService;
	

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public String getConnexion(Model model, HttpServletRequest request) {
		model.addAttribute("connexion", CONNEXION);
		return "shoppingonlineconnexion";
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String sumitConnexion(Model model) {
		int basketSize = 1;
		model.addAttribute("basketSize", basketSize);
		model.addAttribute("basket", BASKET);

		return "shoppingonlinehomeclient";
	}
	
	@RequestMapping(value = "/connect", method = RequestMethod.GET)
	public String getShoppingOnlineHomeClient(Model model, HttpServletRequest request) {
		model.addAttribute("connexion", CONNEXION);
		return "shoppingonlinehomeclient";
	}

	@RequestMapping(value = "/showbasket", method = RequestMethod.GET)
	public String getBasket() {
		return "shoppingonlinebasket";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String getNewUser(Model model) {
		model.addAttribute("newuser", NEWUSER);
		return "shoppingonlinenewuser";
	}

	@RequestMapping("/addtobasket/{itemId}")
	public String addToBasket(@PathVariable Long itemId, HttpServletRequest request) {
		infoBasket(itemId, request);
		return "shoppingonlinehome";
	}
	
	@RequestMapping("/addtobasketfromresultsearch/{itemId}")
	public String addToBasketFromResultSearch(@PathVariable Long itemId, HttpServletRequest request) {
		infoBasket(itemId, request);
		return "shoppingonlinesearchresult";
	}
	
	@RequestMapping("/payitems")
	private String payItems(HttpServletRequest request) {
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		
		if (basket==null){
			return "redirect:/";
		}
		
		shoppingService.removeItemsToBasket();
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0);
		
		return "shoppingonlinebasketreceived";
	}
	
	@RequestMapping("/removefrombasket/{itemId}")
	private String removeItemFromBasket(@PathVariable Long itemId, HttpServletRequest request) {
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		
		if (basket==null){
			return "redirect:/";
		}
		
		
		boolean b = false;
		int i = 0;

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
	
		return "shoppingonlinebasket";
	}
	
	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String getDeconnexion(Model model, HttpServletRequest request, WebRequest webrequest, SessionStatus sessionstatus) {
		
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
		
		return "redirect:/";
	}
	
	public void infoBasket(Long itemId, HttpServletRequest request){
		shoppingService.addItemToBasket(itemId);
		request.getSession().setAttribute("basket", shoppingService.getBasket());
		request.getSession().setAttribute("basketsize", shoppingService.getBasket().size());
		request.getSession().setAttribute("baskettotal", shoppingService.getTotalBasket());
		
	}
	
}
