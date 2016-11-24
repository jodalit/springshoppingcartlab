package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
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
			System.out.println("request.getContextPath() : "
					+ request.getContextPath().substring(1, request.getContextPath().length()));
			// System.out.println("request.getContextPath() : "+
			// request.getContextPath());
		}
		return "shoppingonlinehome";
	}

	private Long Long(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String getDeconnexion(Model model, HttpServletRequest request) {
		model.addAttribute("connexionname", null);
		// request.getSession().setAttribute("items", null);
		// request.getSession().setAttribute("basket", null);
		return "redirect:/";
	}
}
