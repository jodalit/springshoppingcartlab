package com.formatiointerne.springmvc.pratiq1.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formatiointerne.springmvc.pratiq1.Conversion.ConvertStringToDouble;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@Controller
public class ShoppingOnlineItem {
	public static final String ADDNEWITEM ="Add New Item";
	public static final String ALLITEMS ="List of all items";
	public static final String UPDATEITEM ="Updating Item : ";
	public static final String SAVERESULT ="Item : ";
	public Long identifiant = 0l;
	
	@Autowired
	ItemService itemService;
	
	
	@RequestMapping(value="/additem", method=RequestMethod.GET)
	public String getAddItem(Model model){
		model.addAttribute("addnewitem", ADDNEWITEM);
		model.addAttribute("saveresult", null);
		
		return "shoppingonlinenewitem";
	}
	
	@RequestMapping(value="/saveitem", method=RequestMethod.POST)
	public String getSaveItem(Model model,@RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, HttpServletRequest request){
		identifiant= itemService.getMaxItemId() +1;
		itemService.createItem(identifiant+"", itemName, itemDescription, itemPrice, itemExpireDate);
		model.addAttribute("saveresult", SAVERESULT);
		model.addAttribute("Itemnumero ", identifiant);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		
		return "shoppingonlinelistofallitems";
	}
	
	@RequestMapping(value="/listitems", method=RequestMethod.GET)
	public String getAllItems(Model model, HttpServletRequest request){
		model.addAttribute("allitems", ALLITEMS);
		model.addAttribute("saveresult", null);
		
		return "shoppingonlinelistofallitems";
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.GET)
	public String showDetailOfItems(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		model.addAttribute("item", itemService.getItemById(itemId));
		model.addAttribute("saveresult", null);
		return "shoppingonlinedetailofitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.GET)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		model.addAttribute("item", itemService.getItemById(itemId));		
		model.addAttribute("updateitem", UPDATEITEM);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		
		return "shoppingonlineupdateitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.POST)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, @RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, Model model, HttpServletRequest request){
		if (itemService.modifyNameDescriptionPriceExpiredateItem(itemId , itemName, itemDescription, itemPrice, itemExpireDate)){
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
		
		
		return "redirect:/listitems";
	}
	
	@RequestMapping(value="/removeitem/{itemId}", method=RequestMethod.GET)
	public String removeDetailItem(@PathVariable("itemId") Long itemId, Model model, HttpServletRequest request){
		if (itemService.removeItem(itemId)){
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
		
		return "redirect:/listitems";
	}
	
}
