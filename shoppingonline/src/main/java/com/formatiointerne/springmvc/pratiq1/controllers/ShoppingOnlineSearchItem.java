package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.List;

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
	ShoppingServiceImplementation shoppingServiceImplementation;
	@Autowired
	private ItemServiceImplementation itemService;
	
	HttpSession sessionItems;
	

	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.GET)
	public String getSearchItem(Model model){
		model.addAttribute("resultSearch", RESULTSEARCHTITLE);
		
		return "shoppingonlinesearchresult";
	}
	
	@RequestMapping(value = "/resultsearchitem", method = RequestMethod.POST)
	public String getResultSearchItem(Model model, @RequestParam("itemNameToFind") String itemNameToFind){
		List<Item> items = itemService.items;
		List<Item> itemsforname = itemService.getItemByName(itemNameToFind);
		int sizeitems = items.size();
		int sizeitemsforname = itemsforname.size();
		
		//sessionItems.setAttribute("items", items);
		model.addAttribute("resultSearch", RESULTSEARCHTITLE);
		model.addAttribute("numberItem", "0");
		model.addAttribute("items1", items);
		model.addAttribute("itemsforname", itemsforname);
		model.addAttribute("sizeitems", sizeitems);
		model.addAttribute("sizeitemsforname", sizeitemsforname);
		
		System.out.println("items : "+items.toString());
		System.out.println("itemsforname : "+itemsforname.toString());
		//System.out.println("sessionItems : "+sessionItems.getAttribute("items"));
		System.out.println("itemNameToFind : "+ itemNameToFind);
		System.out.println("sizeitemsforname : "+ sizeitemsforname);
		return "shoppingonlinesearchresult";
	}
}
