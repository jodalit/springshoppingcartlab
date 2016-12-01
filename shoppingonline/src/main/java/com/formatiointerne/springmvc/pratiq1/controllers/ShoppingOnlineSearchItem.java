package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@Controller
@Scope("session")
public class ShoppingOnlineSearchItem {
	public static final String RESULTSEARCHTITLE ="Search result";
	public static final String NUMBERITEMS ="Search result";
	@Autowired
	private ItemServiceImplementation itemService;
	@Autowired
	ShoppingServiceImplementation shoppingServiceImplementation;
 	
	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.GET)
	public String getSearchItem(HttpServletRequest request){
		request.getSession().setAttribute("resultSearch", RESULTSEARCHTITLE);
		
		return "shoppingonlinesearchresult";
	}
	
	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.POST)
	public String getResultSearchItem(@RequestParam("itemNameToFind") String itemNameToFind, HttpServletRequest request){
	 
		Set<Item> itemsforname = itemService.getItemByNameDescription(itemNameToFind);
	
		int sizeitemsforname = itemsforname.size();
	
		request.getSession().setAttribute("resultSearch", RESULTSEARCHTITLE);
		request.getSession().setAttribute("numberItem", "0");
	
		request.getSession().setAttribute("itemsforname", itemsforname);
		request.getSession().setAttribute("sizeitemsforname", sizeitemsforname);
		
		return "shoppingonlinesearchresult";
	}
}
