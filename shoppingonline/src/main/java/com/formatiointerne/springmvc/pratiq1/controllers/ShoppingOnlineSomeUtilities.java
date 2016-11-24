package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@Controller
public class ShoppingOnlineSomeUtilities {
	public static final String CONNEXION = "Connexion to your on line store";
	public static final String BASKET = "Basket Breakdown";
	public static final String NEWUSER = "Choose your profil";

	@Autowired
	private ShoppingServiceImplementation shoppingServiceImplementation;

	@Autowired
	private ItemServiceImplementation itemService;

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public String getConnexion(Model model) {
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
		/*System.out.println("!!!! I SHOULD BE CALLED ONLY ONCE!!!!!");
		System.out.println("itemId : " + itemId);
		List<Item> items = (List<Item>) request.getSession().getAttribute("items");
		System.out.println("items : " + items);
		boolean b = false;

		for (Item item : items) {
			if (item.getItemId().equals(itemId)) {
				System.out.println("item : " + item);
				b = true;
				break;
			}
		}

		if (b == true) {
			shoppingServiceImplementation.addItemToBasket(itemId);
			request.getSession().setAttribute("items", items);
			request.getSession().setAttribute("basket", shoppingServiceImplementation.basket);
			request.getSession().setAttribute("basketsize", shoppingServiceImplementation.basket.size());
			System.out.println("request.getServletContext().getServletContextName()"+ request.getServletContext().getServletContextName());
		}*/
		infoBasket(itemId, request);
		return "shoppingonlinehome";
	}
	
	public void infoBasket(Long itemId, HttpServletRequest request){
		System.out.println("!!!! I SHOULD BE CALLED ONLY ONCE!!!!!");
		System.out.println("itemId : " + itemId);
		List<Item> items = (List<Item>) request.getSession().getAttribute("items");
		System.out.println("items : " + items);
		boolean b = false;

		for (Item item : items) {
			if (item.getItemId().equals(itemId)) {
				System.out.println("item : " + item);
				b = true;
				break;
			}
		}

		if (b == true) {
			shoppingServiceImplementation.addItemToBasket(itemId);
			request.getSession().setAttribute("items", items);
			request.getSession().setAttribute("basket", shoppingServiceImplementation.basket);
			request.getSession().setAttribute("basketsize", shoppingServiceImplementation.basket.size());
			//System.out.println("request.getServletContext().getServletContextName()"+ request.getServletContext().getServletContextName());
		}
	}
	
	@RequestMapping("/addtobasketfromresultsearch/{itemId}")
	public String addToBasketFromResultSearch(@PathVariable Long itemId, HttpServletRequest request) {
		infoBasket(itemId, request);
		return "redirect:/";
	}
	
	@RequestMapping("/payitems")
	private String payItems(HttpServletRequest request) {
		List<Item> basket = (List<Item>) request.getSession().getAttribute("basket");
		basket.forEach(System.out::println);
		shoppingServiceImplementation.removeItemToBasket();
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		
		return "shoppingonlinesearchresult";
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String getDeconnexion(Model model, HttpServletRequest request) {
		model.addAttribute("connexionname", null);
		// request.getSession().setAttribute("items", null);
		// request.getSession().setAttribute("basket", null);
		return "redirect:/";
	}
}
