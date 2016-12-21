package com.alithya.shoppingcart.controller;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.BasketService;

@Controller
public class ShoppingOnlineBasketController {
	public static final String SESSION_PEOPLE = "people";
	public static final String SESSION_ITEMS = "items";
	public static final String SESSION_ITEMS_FOR_NAME = "itemsforname";
	public static final String SESSION_CONNECTION_NAME = "connectionname";
	public static final String SESSION_BASKETTOTAL = "baskettotal";
	public static final String SESSION_BASKETSIZE = "basketsize";
	public static final String MODEL_NAME_NEWUSER = "newuser";
	public static final String MODEL_NAME_BASKET = "basket";
	public static final String MODEL_NAME_BASKET_SIZE = "basketSize";
	public static final String MODEL_NAME_CONNECTION = "connection";
	public static final String REQUESTMAPPING_DECONNECTION = "/deconnection";
	public static final String REQUESTMAPPING_REMOVEFROMBASKET_ITEM_ID = "/removefrombasket/{itemId}";
	public static final String REQUESTMAPPING_PAYITEMS = "/payitems";
	public static final String REQUESTMAPPING_ADD_TO_BASKET_FROM_RESULT_SEARCH_ITEM_ID = "/addtobasketfromresultsearch/{itemId}";
	public static final String REQUESTMAPPING_ADDTOBASKET_ITEM_ID = "/addtobasket/{itemId}";
	public static final String REQUESTMAPPING_NEWUSER = "/newuser";
	public static final String REQUESTMAPPING_SHOWBASKET = "/showbasket";
	public static final String REQUESTMAPPING_CONNECT = "/connect";
	public static final String REQUESTMAPPING_CONNECTION = "/connection";
	
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_BASKET_RECEIVED = "shoppingonlinebasketreceived";
	public static final String SHOPPING_ONLINE_SEARCH_RESULT = "shoppingonlinesearchresult";
	public static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";
	public static final String SHOPPING_ONLINE_NEWUSER = "shoppingonlinenewuser";
	public static final String SHOPPING_ONLINE_BASKET = "shoppingonlinebasket";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String SHOPPING_ONLINE_CONNEXION = "shoppingonlineconnexion";
	public static final String CONNEXION = "Access to your account";
	public static final String BASKET = "Basket Breakdown";
	
	@Autowired
	private BasketService basketService;

	@RequestMapping(value = REQUESTMAPPING_SHOWBASKET, method = RequestMethod.GET)
	public String getBasket() {
		
		return SHOPPING_ONLINE_BASKET;
	}
		
	@RequestMapping(REQUESTMAPPING_ADDTOBASKET_ITEM_ID)
	public String addToBasket(@PathVariable Long itemId, HttpServletRequest request) {
		
		if (basketService.addItemToBasket(itemId)){
			infoBasket(itemId, request);
		}
		
		return SHOPPING_ONLINE_HOME;
	}
	
	@RequestMapping(REQUESTMAPPING_ADD_TO_BASKET_FROM_RESULT_SEARCH_ITEM_ID)
	public String addToBasketFromResultSearch(@PathVariable Long itemId, HttpServletRequest request) {
		
		if (basketService.addItemToBasket(itemId)){
			infoBasket(itemId, request);
		}
		
		return SHOPPING_ONLINE_SEARCH_RESULT;
	}
	
	@RequestMapping(REQUESTMAPPING_PAYITEMS)
	public String payItems(HttpServletRequest request) {
		
		if (basketService.getItemsBasket() == null)
			return REDIRECT;
		
		if (basketService.removeItemsToBasket()){
			initBasket(request);
			return REDIRECT;
		}
			
		return SHOPPING_ONLINE_BASKET_RECEIVED;
	}
	
	@RequestMapping(REQUESTMAPPING_REMOVEFROMBASKET_ITEM_ID)
	public String removeItemFromBasket(@PathVariable Long itemId, HttpServletRequest request) {
		
		Collection<Item> basket = (Collection<Item>) request.getSession().getAttribute(MODEL_NAME_BASKET);
		
		if (basket==null)
			return REDIRECT;
		
		if (basketService.removeItemToBasket(itemId)){
			infoBasket(itemId, request);
		}
	
		return SHOPPING_ONLINE_BASKET;
	}
	
	public void infoBasket(Long itemId, HttpServletRequest request){
			request.getSession().setAttribute(MODEL_NAME_BASKET, basketService.getItemsBasket().values());
			request.getSession().setAttribute(SESSION_BASKETSIZE, basketService.getItemsBasket().size());
			request.getSession().setAttribute(SESSION_BASKETTOTAL, basketService.getTotalAmountBasket());
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute(MODEL_NAME_BASKET, basketService.initBasket().get(1));
		request.getSession().setAttribute(SESSION_BASKETSIZE, basketService.initBasket().get(2));
		request.getSession().setAttribute(SESSION_BASKETTOTAL, basketService.initBasket().get(3));
	}
	
	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}
}
