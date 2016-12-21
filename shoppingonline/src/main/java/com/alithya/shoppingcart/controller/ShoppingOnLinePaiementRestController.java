package com.alithya.shoppingcart.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.PaiementService;

@RestController
@RequestMapping(ShoppingOnLinePaiementRestController.SESSION_CUSTOMER)
public class ShoppingOnLinePaiementRestController {
	public static final String ERROR_CUSTOMER_AVAILABLE_AMOUNT = "customerAvailableAmount";
	public static final String ERROR_EMPTYBASKET = "emptybasket";
	public static final String ERROR_ACCOUNTBALANCE = "accountbalance";
	public static final String SESSION_CUSTOMER = "customer";
	public static final String SESSION_BASKETDATA = "basketdata";
	public static final String STRING_H1 = "</h1>";
	public static final String STRING_BR = "<br />";
	public static final String STRING_H1_STYLE_COLOR_RED_WARNNING_H1 = "<h1 style='color:red;'>WARNNING</h1>";
	public static final String STRING_H3_STYLE_COLOR_BLEUE_YOUR_RECHARGE_IS_PERFORMED_WITH_SUCCESS_H3 = "<h3 style='color:bleue;'>Your recharge is performed with success.</h3>";
	public static final String STRING_H2_STYLE_COLOR_GREEN_INFO = "<h2 style='color:green;'>INFO : ";
	public static final String STRING_H3_STYLE_COLOR_BLEUE_FOR_THESE_ITEMS_H3 = "<h3 style='color:bleue;'>For these Items : </h3>";
	public static final String STRING_H2 = "</h2>";
	public static final String STRING_H2_STYLE_COLOR_GREEN_YOUR_TICKET_$ = "<h2 style='color:green;'>Your ticket ($) : ";
	public static final String STRING_H1_STYLE_COLOR_RED = "<h1 style='color:red;'>";
	
	@Autowired
	private PaiementService paiementService; 
	
	@RequestMapping(value="/payitems", method=RequestMethod.GET)
	@ResponseBody
	public String payItems(HttpServletRequest request) {
		
		Basket basketData = (Basket) request.getSession().getAttribute(SESSION_BASKETDATA);
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		Map<String, String> errors;
				
		Double basketTotalAmount = basketData.getBasketTotalAmount();
		Collection<Item> items = basketData.getBasketItems().values();
		
		if (!paiementService.purchaseItem(basketData, customer)){
			errors = paiementService.getErrors();
			return String.join(" ",STRING_H1_STYLE_COLOR_RED, errors.get(ERROR_ACCOUNTBALANCE),STRING_BR,errors.get(ERROR_EMPTYBASKET), STRING_H1);
		}
		
		initBasket(request);
		request.getSession().invalidate();
		
		return String.join(" ", STRING_H2_STYLE_COLOR_GREEN_YOUR_TICKET_$, basketTotalAmount.toString(),STRING_H2, STRING_H3_STYLE_COLOR_BLEUE_FOR_THESE_ITEMS_H3, items.toString()) ;
	}
	
	@RequestMapping(value="/recharge", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public String rechargeAccount(@RequestParam(ERROR_CUSTOMER_AVAILABLE_AMOUNT) Double customerAvailableAmount, HttpServletRequest request) {
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		Map<String, String> errors;
		
		if (!paiementService.recharge(customerAvailableAmount, customer.getCustomerId())){
			errors = paiementService.getErrors();
			return String.join(" ",STRING_H1_STYLE_COLOR_RED_WARNNING_H1, errors.get(ERROR_ACCOUNTBALANCE),STRING_BR,errors.get(ERROR_EMPTYBASKET), STRING_H1);
		}
			
		return String.join(" ", STRING_H2_STYLE_COLOR_GREEN_INFO, STRING_H2, STRING_H3_STYLE_COLOR_BLEUE_YOUR_RECHARGE_IS_PERFORMED_WITH_SUCCESS_H3) ;
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute(SESSION_BASKETDATA,null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0.0);
	}

	public void setPaiementService(PaiementService paiementService) {
		this.paiementService = paiementService;
	}
	
}
