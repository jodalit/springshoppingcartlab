package com.alithya.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ShoppingOnLineAuthenticationController {
	public static final String SESSION_PEOPLE = "people";
	public static final String SESSION_ITEMS_FOR_NAME = "itemsforname";
	public static final String SESSION_CONNECTION_NAME = "connectionname";
	public static final String SESSION_BASKETDATA = "basketdata";
	public static final String MODEL_NAME_NEWUSER = "newuser";
	public static final String MODEL_NAME_BASKET = "basket";
	public static final String MODEL_NAME_BASKET_SIZE = "basketSize";
	public static final String MODEL_NAME_CONNECTION = "connection";
	public static final String REQUESTMAPPING_DECONNECTION = "/deconnection";
	public static final String REQUESTMAPPING_CONNECT = "/connect";
	public static final String REQUESTMAPPING_CONNECTION = "/connection";
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_HOME_ADMIN = "shoppingonlinehomeadmin";
	public static final String SHOPPING_ONLINE_CONNEXION = "shoppingonlineconnexion";
	public static final String CONNEXION = "Access to your account";
	public static final String REQUESTMAPPING_ADMIN = "/admin";
	public static final String MODEL_NAME_ADMIN = "admin";
	public static final String ADMINISTRATION ="You're Administrator !!!";
		
	@RequestMapping(value=REQUESTMAPPING_ADMIN, method=RequestMethod.GET)
	public String getNewClient(ModelMap model){
		
		model.addAttribute(MODEL_NAME_ADMIN, ADMINISTRATION);
		
		return SHOPPING_ONLINE_HOME_ADMIN;
	}
	
	@RequestMapping(value = REQUESTMAPPING_CONNECTION, method = RequestMethod.GET)
	public String getConnection(ModelMap model, HttpServletRequest request) {
				
		return SHOPPING_ONLINE_CONNEXION;
	}

	@RequestMapping(value = REQUESTMAPPING_CONNECTION, method = RequestMethod.POST)
	public String sumitConnection(ModelMap model) {
		
		return SHOPPING_ONLINE_HOME_ADMIN;
	}
	
	@RequestMapping(value = REQUESTMAPPING_CONNECT, method = RequestMethod.GET)
	public String getShoppingOnlineHomeAdmin(ModelMap model, HttpServletRequest request) {
		
		model.addAttribute(MODEL_NAME_CONNECTION, CONNEXION);
		
		return SHOPPING_ONLINE_HOME_ADMIN;
	}

	@RequestMapping(value = REQUESTMAPPING_DECONNECTION, method = RequestMethod.GET)
	public String getDeconnexion(ModelMap model, HttpServletRequest request, WebRequest webrequest, SessionStatus sessionstatus) {
		
		request.getSession().setAttribute(SESSION_CONNECTION_NAME, null);
		request.getSession().setAttribute(SESSION_ITEMS_FOR_NAME, null);
		request.getSession().setAttribute(SESSION_PEOPLE, null);
		request.getSession().setAttribute(SESSION_BASKETDATA, null);
		
		sessionstatus.setComplete();
		
		webrequest.removeAttribute(SESSION_CONNECTION_NAME, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_ITEMS_FOR_NAME, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(MODEL_NAME_BASKET, WebRequest.SCOPE_SESSION);
		webrequest.removeAttribute(SESSION_PEOPLE, WebRequest.SCOPE_SESSION);
		
		return REDIRECT;
	}
	
}
