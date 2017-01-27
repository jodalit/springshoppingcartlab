package com.alithya.shoppingcart.controller;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.webservice.consumer.FinancialConsumer;

@Controller
@RequestMapping(ShoppingOnLineFinancialRestController.SESSION_CUSTOMER)
public class ShoppingOnLineFinancialWsController {
	public static final String CUSTOMER_AVAILABLE_AMOUNT = "customerAvailableAmount";
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
	FinancialConsumer financialConsumer;
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/payitemsbyws/{basketReference}", method=RequestMethod.GET)
	@ResponseBody
	public String payItemsByWebService(@PathVariable String basketReference, HttpServletRequest request) {
		
		Basket basketData = (Basket) request.getSession().getAttribute(SESSION_BASKETDATA);
		Double basketTotalAmount = basketData.getBasketTotalAmount();
		Collection<Item> items = basketData.getBasketItems().values();
		
		if (financialConsumer.doPurchaseItems(basketReference)==null){
			return String.join(" ",STRING_H1_STYLE_COLOR_RED, ERROR_ACCOUNTBALANCE,STRING_BR,ERROR_EMPTYBASKET, STRING_H1);
		}
		
		initBasket(request);
		request.getSession().invalidate();
		
		return String.join(" ", STRING_H2_STYLE_COLOR_GREEN_YOUR_TICKET_$, basketTotalAmount.toString(),STRING_H2, STRING_H3_STYLE_COLOR_BLEUE_FOR_THESE_ITEMS_H3, items.toString()) ;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/rechargebyws", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public String rechargeAccountByWebService(@RequestParam(CUSTOMER_AVAILABLE_AMOUNT) Double customerAvailableAmount, HttpServletRequest request) {
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		
		if (financialConsumer.doRecharge(customerAvailableAmount, customer.getCustomerId())==null){
			return String.join(" ",STRING_H1_STYLE_COLOR_RED_WARNNING_H1, ERROR_ACCOUNTBALANCE,STRING_BR,ERROR_EMPTYBASKET, STRING_H1);
		}
			
		return String.join(" ", STRING_H2_STYLE_COLOR_GREEN_INFO, STRING_H2, STRING_H3_STYLE_COLOR_BLEUE_YOUR_RECHARGE_IS_PERFORMED_WITH_SUCCESS_H3) ;
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute(SESSION_BASKETDATA,null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0.0);
	}

	public void setFinancialConsumer(FinancialConsumer financialConsumer) {
		this.financialConsumer = financialConsumer;
	}
}
