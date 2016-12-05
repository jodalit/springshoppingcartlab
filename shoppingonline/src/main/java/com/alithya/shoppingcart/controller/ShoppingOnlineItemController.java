package com.alithya.shoppingcart.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ItemServiceImplementation;

@Controller
public class ShoppingOnlineItemController {
	public static final String ADDNEWITEM ="Add New Item";
	public static final String ALLITEMS ="List of all items";
	public static final String UPDATEITEM ="Updating Item : ";
	public static final String SAVERESULT ="Item : ";
	public Long identifiant = 0l;
	
	@Autowired
	ItemService itemService;
	
	
	@RequestMapping(value="/additem", method=RequestMethod.GET)
	public String addItem(ModelMap model){
		model.addAttribute("addnewitem", ADDNEWITEM);
		model.addAttribute("saveresult", null);
		
		return "shoppingonlinenewitem";
	}
	
	@RequestMapping(value="/saveitem", method=RequestMethod.POST)
	public String getSaveItem(ModelMap model,@RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, HttpServletRequest request){
		identifiant= itemService.getMaxItemId() +1;
		itemService.createItem(identifiant+"", itemName, itemDescription, itemPrice, itemExpireDate);
		model.addAttribute("saveresult", SAVERESULT);
		model.addAttribute("Itemnumero", identifiant);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		return "shoppingonlinelistofallitems";
	}
	
	@RequestMapping(value="/listitems", method=RequestMethod.GET)
	public String getAllItems(ModelMap model, HttpServletRequest request){
		model.addAttribute("allitems", ALLITEMS);
	
		return "shoppingonlinelistofallitems";
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.GET)
	public String showDetailOfItems(@PathVariable("itemId") Long itemId, ModelMap model, HttpServletRequest request){
		model.addAttribute("item", itemService.getItemById(itemId));
		return "shoppingonlinedetailofitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.GET)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, ModelMap model, HttpServletRequest request){
		model.addAttribute("item", itemService.getItemById(itemId));		
		model.addAttribute("updateitem", UPDATEITEM);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		
		return "shoppingonlineupdateitem";
	}
	
	@RequestMapping(value="/updateitem/{itemId}", method=RequestMethod.POST)
	public String updateDetailItem(@PathVariable("itemId") Long itemId, @RequestParam("itemName") String itemName, @RequestParam("itemDescription") String itemDescription, @RequestParam("itemPrice") String itemPrice, @RequestParam("itemExpireDate") String itemExpireDate, ModelMap model, HttpServletRequest request){
		if (itemService.modifyNameDescriptionPriceExpiredateItem(itemId , itemName, itemDescription, itemPrice, itemExpireDate)){
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
		
		
		return "redirect:/listitems";
	}
	
	@RequestMapping(value="/removeitem/{itemId}", method=RequestMethod.GET)
	public String removeDetailItem(@PathVariable("itemId") Long itemId, ModelMap model, HttpServletRequest request){
		if (itemService.removeItem(itemId)){
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
		
		return "redirect:/listitems";
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	
}
