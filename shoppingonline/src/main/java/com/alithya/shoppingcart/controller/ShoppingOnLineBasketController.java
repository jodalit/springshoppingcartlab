package com.alithya.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.FinancialService;

@Controller
public class ShoppingOnLineBasketController {
	public static final String SESSION_CUSTOMER = "customer";
	public static final String SESSION_BASKETDATA = "basketdata";
	public static final String REQUESTMAPPING_REMOVEFROMBASKET_ITEM_ID = "/removefrombasket/{itemId}";
	public static final String REQUESTMAPPING_ADD_TO_BASKET_FROM_RESULT_SEARCH_ITEM_ID = "/addtobasketfromresultsearch/{itemId}";
	public static final String REQUESTMAPPING_ADDTOBASKET_ITEM_ID = "/addtobasket/{itemId}";
	public static final String REQUESTMAPPING_SHOWBASKET = "/showbasket";
	public static final String REDIRECT = "redirect:/";
	public static final String SHOPPING_ONLINE_SEARCH_RESULT = "shoppingonlinesearchresult";
	public static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";
	public static final String SHOPPING_ONLINE_BASKET = "shoppingonlinebasket";
		
	@Autowired
	private BasketService basketService;
	
	@Autowired
	FinancialService financialService;
	
	@Secured("ROLE_USER")
	@RequestMapping(value = REQUESTMAPPING_SHOWBASKET, method = RequestMethod.GET)
	public String getBasket(HttpServletRequest request) {
		request.getSession().setAttribute(SESSION_CUSTOMER, financialService.getCustomerInfo());
		return SHOPPING_ONLINE_BASKET;
	}
	
	@RequestMapping( value =REQUESTMAPPING_ADDTOBASKET_ITEM_ID, method = RequestMethod.GET)
	public String addToBasket(@PathVariable Long itemId, HttpServletRequest request) {		
		addBasketToSession(itemId, request);
		return SHOPPING_ONLINE_HOME;
	}
	
	@RequestMapping(value =REQUESTMAPPING_ADD_TO_BASKET_FROM_RESULT_SEARCH_ITEM_ID, method = RequestMethod.GET)
	public String addToBasketFromResultSearch(@PathVariable Long itemId, HttpServletRequest request) {
		addBasketToSession(itemId, request);	
		return SHOPPING_ONLINE_SEARCH_RESULT;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value=REQUESTMAPPING_REMOVEFROMBASKET_ITEM_ID, method = RequestMethod.GET)
	public String removeItemFromBasket(@PathVariable Long itemId, HttpServletRequest request) {
		if (basketService.removeItemToBasket(itemId)){
			request.getSession().setAttribute(SESSION_BASKETDATA, basketService.getBasketData());
		}
	
		return SHOPPING_ONLINE_BASKET;
	}
	
	public void addBasketToSession(Long itemId, HttpServletRequest request){
		if (basketService.addItemToBasket(itemId).getBasketReference() != null){
			request.getSession().setAttribute(SESSION_BASKETDATA, basketService.getBasketData());
		}
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute(SESSION_BASKETDATA, null);
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
}
