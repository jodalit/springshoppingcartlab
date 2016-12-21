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
@RequestMapping("customer")
public class ShoppingOnLinePaiementRestController {
	@Autowired
	private PaiementService paiementService; 
	
	@RequestMapping(value="/payitems", method=RequestMethod.GET)
	@ResponseBody
	public String payItems(HttpServletRequest request) {
		
		
		Basket basketData = (Basket) request.getSession().getAttribute("basketdata");
		/*if (basketData == null)
			return String.join(" ", "<h1 style='color:red;'>", errors.get("emptybasket"),"</h1>");
		*/
		
		
		//Collection<Item> items = basketData.getBasketItems().values();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		if (!paiementService.purchaseItem(basketData, customer)){
			//request.getSession().setAttribute("errors", customerService.getErrors());
			Map<String, String> errors = paiementService.getErrors();
			return String.join(" ","<h1 style='color:red;'>", errors.get("accountbalance"),"<br />",errors.get("emptybasket"), "</h1>");//${errors['accountbalance']}
		}
			
		Double basketTotalAmount = basketData.getBasketTotalAmount();
		Collection<Item> items = basketData.getBasketItems().values();
		
		initBasket(request);
		request.getSession().invalidate();
		
		return String.join(" ", "<h2 style='color:green;'>Your ticket ($) : ", basketTotalAmount.toString(),"</h2>", "<h3 style='color:bleue;'>For these Items : </h3>", items.toString()) ;
	}
	
	@RequestMapping(value="/recharge", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public String rechargeAccount(@RequestParam("customerAvailableAmount") Double customerAvailableAmount, HttpServletRequest request) {
		
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if (!paiementService.recharge(customerAvailableAmount, customer.getCustomerId())){
			//request.getSession().setAttribute("errors", customerService.getErrors());
			Map<String, String> errors = paiementService.getErrors();
			return String.join(" ","<h1 style='color:red;'>WARNNING</h1>", errors.get("accountbalance"),"<br />",errors.get("emptybasket"), "</h1>");
			//return String.join(" ", "<h1 style='color:red;'>WARNNING</h1>", "<h2>Your recharge is not performed !","</h2>");
		}
			
		
		return String.join(" ", "<h2 style='color:green;'>INFO : ", "</h2>", "<h3 style='color:bleue;'>Your recharge is performed with success.</h3>") ;
	}
	
	public void initBasket(HttpServletRequest request){
		request.getSession().setAttribute("basketdata",null);
		request.getSession().setAttribute("basket", null);
		request.getSession().setAttribute("basketsize", 0);
		request.getSession().setAttribute("baskettotal", 0.0);
	}
}
