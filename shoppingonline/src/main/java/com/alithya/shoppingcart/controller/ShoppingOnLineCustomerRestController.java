package com.alithya.shoppingcart.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.CustomerService;

@RestController
@RequestMapping("customer")
public class ShoppingOnLineCustomerRestController {
	@Autowired
	private CustomerService customerService; 
	
	@RequestMapping(value="/payitems", method=RequestMethod.GET)
	@ResponseBody
	public String payItems(HttpServletRequest request) {
	
		Basket basketData = (Basket) request.getSession().getAttribute("basketdata");
		if (basketData == null)
			return "<h1 style='color:red;'>Your basket is empty</h1>";
		
		Double basketTotalAmount = basketData.getBasketTotalAmount();
		Collection<Item> items = basketData.getBasketItems().values();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		if (!customerService.purchaseItem(basketData, customer))
			return String.join(" ", "<h1 style='color:red;'>You do not have enough money available in your Account</h1>", 
					"<h2>Your available amount is ($) :",customer.getCustomerAvailableAmount().toString(), "</h2>");
		
		initBasket(request);
		request.getSession().invalidate();
		
		return String.join(" ", "<h2 style='color:green;'>Your ticket ($) : ", 
				basketTotalAmount.toString(),"</h2>", "<h3 style='color:bleue;'>For these Items : </h3>", 
				items.toString()) ;
	}
	
	@RequestMapping(value="/recharge", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void rechargeAccount(@PathVariable("customerAvailableAmount") Double customerAvailableAmount, RequestBody Customer, HttpServletRequest request) {
		
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute("basketdata",null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0.0);
	}
}
