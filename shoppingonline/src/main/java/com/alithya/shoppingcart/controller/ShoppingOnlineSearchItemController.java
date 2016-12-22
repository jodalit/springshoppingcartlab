package com.alithya.shoppingcart.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ShoppingService;

@Controller
public class ShoppingOnlineSearchItemController {
	
	public static final String SESSION_SIZE_ITEMS_FOR_NAME = "sizeitemsforname";
	public static final String SESSION_ITEMS_FOR_NAME = "itemsforname";
	public static final String SESSION_NUMBER_ITEM = "numberItem";
	public static final String SESSION_RESULT_SEARCH = "resultSearch";
	public static final String REQUESTMAPPING_RESULT_SEARCH_ITEM = "/resultsearchitem";
	public static final String SHOPPING_ONLINE_SEARCH_RESULT = "shoppingonlinesearchresult";
	public static final String RESULTSEARCHTITLE ="Search result";
	
	@Autowired
	private ItemService itemService;
	@Autowired
	ShoppingService shoppingService;
 	
	@RequestMapping(value = REQUESTMAPPING_RESULT_SEARCH_ITEM, method = RequestMethod.GET)
	public String getSearchItem(HttpServletRequest request){
		request.getSession().setAttribute(SESSION_RESULT_SEARCH, RESULTSEARCHTITLE);
		
		return SHOPPING_ONLINE_SEARCH_RESULT;
	}
	
	@RequestMapping(value = REQUESTMAPPING_RESULT_SEARCH_ITEM, method = RequestMethod.POST)
	public String getResultSearchItem(@RequestParam("itemNameToFind") String itemNameToFind, HttpServletRequest request){
	 
		Set<Item> itemsforname = itemService.getItemByNameDescription(itemNameToFind);
	
		int sizeitemsforname = itemsforname.size();
	
		request.getSession().setAttribute(SESSION_RESULT_SEARCH, RESULTSEARCHTITLE);
		request.getSession().setAttribute(SESSION_NUMBER_ITEM, "0");
	
		request.getSession().setAttribute(SESSION_ITEMS_FOR_NAME, itemsforname);
		request.getSession().setAttribute(SESSION_SIZE_ITEMS_FOR_NAME, sizeitemsforname);
		
		return SHOPPING_ONLINE_SEARCH_RESULT;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
}
