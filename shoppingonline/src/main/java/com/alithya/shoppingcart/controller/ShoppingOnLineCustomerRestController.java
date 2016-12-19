package com.alithya.shoppingcart.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping("/payitems")
	@ResponseBody
	public String payItems(HttpServletRequest request) {
	
		Basket basketData = (Basket) request.getSession().getAttribute("basketdata");
		if (basketData == null)
			return "<h1 style='color:red;'>Your basket is empty</h1>";
		
		Double basketTotalAmount = basketData.getBasketTotalAmount();
		Collection<Item> items = basketData.getBasketItems().values();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (!customerService.purchaseItem(basketData, customer))
			return String.join(" ", "<h1 style='color:red;'>You do not have enough money available in your Account</h1>", "<h2>Your available amount is ($) :",customer.getCustomerAvailableAmount().toString(), "</h2>");
		
		initBasket(request);
		request.getSession().invalidate();
		
		return String.join(" ", "<h2>Your ticket ($) : ", basketTotalAmount.toString(),"</h2>", "<h3>For these Items : </h3>", items.toString()) ;
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute("basketdata",null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0.0);
	}
}
