package com.alithya.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ShoppingService;

@Controller
public class ShoppingOnlineSomeUtilitiesController {
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
	public static final String NEWUSER = "Choose your profil";
	
	@RequestMapping(value = REQUESTMAPPING_CONNECTION, method = RequestMethod.GET)
	public String getConnection(ModelMap model, HttpServletRequest request) {
		
		model.addAttribute(MODEL_NAME_CONNECTION, CONNEXION);
		
		return SHOPPING_ONLINE_CONNEXION;
	}

	@RequestMapping(value = REQUESTMAPPING_CONNECTION, method = RequestMethod.POST)
	public String sumitConnection(ModelMap model) {
		
		model.addAttribute(MODEL_NAME_BASKET, BASKET);

		return SHOPPING_ONLINE_HOME_ADMIN;
	}
	
	@RequestMapping(value = REQUESTMAPPING_CONNECT, method = RequestMethod.GET)
	public String getShoppingOnlineHomeAdmin(ModelMap model, HttpServletRequest request) {
		
		model.addAttribute(MODEL_NAME_CONNECTION, CONNEXION);
		
		return SHOPPING_ONLINE_HOME_ADMIN;
	}

	@RequestMapping(value = REQUESTMAPPING_NEWUSER, method = RequestMethod.GET)
	public String getNewUser(ModelMap model) {
		
		model.addAttribute(MODEL_NAME_NEWUSER, NEWUSER);
		
		return SHOPPING_ONLINE_NEWUSER;
	}

	@RequestMapping(value = REQUESTMAPPING_DECONNECTION, method = RequestMethod.GET)
	public String getDeconnexion(ModelMap model, HttpServletRequest request, WebRequest webrequest, SessionStatus sessionstatus) {
		
		request.getSession().setAttribute(SESSION_BASKETSIZE, 0);
		request.getSession().setAttribute(SESSION_BASKETTOTAL, 0);
		request.getSession().setAttribute(SESSION_CONNECTION_NAME, null);
		request.getSession().setAttribute(SESSION_ITEMS_FOR_NAME, null);
		request.getSession().setAttribute(SESSION_ITEMS, null);
		request.getSession().setAttribute(MODEL_NAME_BASKET, null);
		request.getSession().setAttribute(SESSION_PEOPLE, null);
		
		
		sessionstatus.setComplete();
		webrequest.removeAttribute(SESSION_BASKETSIZE, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_BASKETTOTAL, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_CONNECTION_NAME, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_ITEMS_FOR_NAME, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_ITEMS, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(MODEL_NAME_BASKET, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_PEOPLE, WebRequest.SCOPE_SESSION);
		
		return REDIRECT;
	}
	
	public void setShoppingService(ShoppingService shoppingService) {
		
	}
	
}
