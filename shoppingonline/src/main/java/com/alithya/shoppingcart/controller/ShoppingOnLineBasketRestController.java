package com.alithya.shoppingcart.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.BasketService;

@RestController
@RequestMapping("rest")
public class ShoppingOnLineBasketRestController {
	@Autowired
	BasketService basketService;
	
	@RequestMapping(value="/basket/{itemId}", method=RequestMethod.GET)
	@ResponseBody
	public Item showDetailOfItems(@PathVariable("itemId") Long itemId, ModelMap model, HttpServletRequest request){
				
		return basketService.getItemsBasket().get(itemId);
	}
	
	@RequestMapping(value="/basket/items", method=RequestMethod.GET)
	@ResponseBody
	public Collection<Item> showItems( ModelMap model, HttpServletRequest request){
				
		return basketService.getItemsBasket().values();
	}

}
