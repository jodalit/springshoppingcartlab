package com.formatiointerne.springmvc.pratiq1.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@Controller
public class ShoppingOnlineItem {
	public static final String ADDNEWITEM ="Add New Item";
	public static final String ALLITEMS ="List of all items";
	public static final String SAVERESULT ="Item : ";
	public int i = 100;
	@Autowired
	ItemServiceImplementation itemService;
	
	@RequestMapping(value="/additem", method=RequestMethod.GET)
	public String getAddItem(Model model){
		model.addAttribute("addnewitem", ADDNEWITEM);
		model.addAttribute("saveresult", null);
		return "shoppingonlinenewitem";
	}
	
	@RequestMapping(value="/saveitem", method=RequestMethod.POST)
	public String getSaveItem(Model model,@RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate){
		
		System.out.println(String.join("//", itemName, itemDescription, itemPrice, itemExpireDate));
		i=i+10;
		itemService.createItem(i+"", itemName, itemDescription, itemPrice, itemExpireDate);
		model.addAttribute("saveresult", SAVERESULT);
		model.addAttribute("Itemnumero ", i);
		return "shoppingonlinenewitem";
	}
	
	@RequestMapping(value="/listitems", method=RequestMethod.GET)
	public String getAllItems(Model model, HttpServletRequest request){
		model.addAttribute("allitems", ALLITEMS);
		model.addAttribute("saveresult", null);
		return "shoppingonlinelistofallitems";
	}
	
	
}
