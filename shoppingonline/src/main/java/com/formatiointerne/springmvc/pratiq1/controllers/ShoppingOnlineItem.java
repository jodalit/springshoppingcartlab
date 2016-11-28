package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@Controller
public class ShoppingOnlineItem {
	public static final String ADDNEWITEM ="Add New Item";
	public static final String ALLITEMS ="List of all items";
	public static final String UPDATEITEM ="Updating Item : ";
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
	public String getSaveItem(Model model,@RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, HttpServletRequest request){
		
		System.out.println(String.join("//", itemName, itemDescription, itemPrice, itemExpireDate));
		i=i+10;
		itemService.createItem(i+"", itemName, itemDescription, itemPrice, itemExpireDate);
		model.addAttribute("saveresult", SAVERESULT);
		model.addAttribute("Itemnumero ", i);
		//request.getSession().setAttribute("items", itemService.items);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.items.values())));
		return "shoppingonlinenewitem";
	}
	
	@RequestMapping(value="/listitems", method=RequestMethod.GET)
	public String getAllItems(Model model, HttpServletRequest request){
		model.addAttribute("allitems", ALLITEMS);
		model.addAttribute("saveresult", null);
		return "shoppingonlinelistofallitems";
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.GET)
	public String showDetailOfItems(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		System.out.println("@PathVariable(itemId) Long itemId : "+itemId);
		System.out.println("itemService.getItemById(itemId) : "+itemService.getItemById(itemId));
		
		model.addAttribute("item", itemService.getItemById(itemId));
		model.addAttribute("saveresult", null);
		return "shoppingonlinedetailofitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.GET)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		System.out.println("@PathVariable(itemId) Long itemId : "+itemId);
		System.out.println("itemService.getItemById(itemId) : "+itemService.getItemById(itemId));
		model.addAttribute("item", itemService.getItemById(itemId));		
		model.addAttribute("updateitem", UPDATEITEM);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.items.values())));
		return "shoppingonlineupdateitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.POST)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, @RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, Model model, HttpServletRequest request){
		
		System.out.println("@PathVariable(itemId) Long itemId : "+itemId);
		
		boolean b = itemService.modifyNameDescriptionPriceExpiredateItem(itemId , itemName, itemDescription, itemPrice, itemExpireDate);
		System.out.println("updateDetailItem(@PathVariable : \n"+b);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.items.values())));
		//request.getSession().setAttribute("items", itemService.items);
		
		return "redirect:/listitems";
	}
	
	@RequestMapping(value="/removeitem/{itemId}", method=RequestMethod.GET)
	public String removeDetailItem(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		System.out.println("@PathVariable(itemId) Long itemId : "+itemId);
		
		boolean b = itemService.removeItem(itemId);
		System.out.println("removeDetailItem(@PathVariabl : \n"+b);
		
		//request.getSession().setAttribute("items", itemService.items);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.items.values())));
		model.addAttribute("saveresult", null);
		//request.getSession().setAttribute("items", itemService.items);
		return "redirect:/listitems";
	}
}
