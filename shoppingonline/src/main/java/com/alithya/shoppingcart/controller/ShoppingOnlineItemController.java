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

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ItemServiceImplementation;

@Controller
public class ShoppingOnlineItemController {
	public static final String REQUESTMAPPING_REMOVEITEM_ITEM_ID = "/removeitem/{itemId}";
	public static final String REQUESTMAPPING_UPDATEITEM_ITEM_ID = "/updateitem/{itemId}";
	public static final String REQUESTMAPPING_ITEM_ID = "/{itemId}";
	public static final String REQUESTMAPPING_LIST_ITEMS = "/listitems";
	public static final String REQUESTMAPPING_SAVE_ITEM = "/saveitem";
	public static final String REQUESTMAPPING_ADD_ITEM = "/additem";
	public static final String ITEM_ID = "itemId";
	public static final String ITEM_EXPIRE_DATE = "itemExpireDate";
	public static final String ITEM_PRICE = "itemPrice";
	public static final String ITEM_DESCRIPTION = "itemDescription";
	public static final String ITEM_NAME = "itemName";
	public static final String REDIRECT_LISTITEMS = "redirect:/listitems";
	public static final String SHOPPING_ONLINE_UPDATE_ITEM = "shoppingonlineupdateitem";
	public static final String SHOPPING_ONLINE_DETAIL_OF_ITEM = "shoppingonlinedetailofitem";
	public static final String SHOPPING_ONLINE_LIST_OF_ALLITEMS = "shoppingonlinelistofallitems";
	public static final String SHOPPING_ONLINE_NEWITEM = "shoppingonlinenewitem";
	public static final String ADDNEWITEM ="Add New Item";
	public static final String ALLITEMS ="List of all items";
	public static final String UPDATEITEM ="Updating Item : ";
	public static final String SAVERESULT ="Item : ";
	public Long identifiant = 0l;
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value=REQUESTMAPPING_ADD_ITEM, method=RequestMethod.GET)
	public String addItem(ModelMap model){
		
		model.addAttribute("addnewitem", ADDNEWITEM);
		model.addAttribute("saveresult", null);
		
		return SHOPPING_ONLINE_NEWITEM;
	}
	
	@RequestMapping(value=REQUESTMAPPING_SAVE_ITEM, method=RequestMethod.POST)
	public String getSaveItem(ModelMap model,@RequestParam(ITEM_NAME) String itemName, @RequestParam(ITEM_DESCRIPTION) String itemDescription, @RequestParam(ITEM_PRICE) String itemPrice, @RequestParam(ITEM_EXPIRE_DATE) String itemExpireDate, HttpServletRequest request){
		
		identifiant= itemService.getMaxItemId() +1;
		Item item = itemService.createItem(identifiant+"", itemName, itemDescription, itemPrice, itemExpireDate);
		
		if (item == null)
			return SHOPPING_ONLINE_NEWITEM;
		
		model.addAttribute("saveresult", SAVERESULT);
		model.addAttribute("Itemnumero", identifiant);
		model.addAttribute("item", item);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		
		return SHOPPING_ONLINE_LIST_OF_ALLITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_LIST_ITEMS, method=RequestMethod.GET)
	public String getAllItems(ModelMap model, HttpServletRequest request){
		
		model.addAttribute("allitems", ALLITEMS);
	
		return SHOPPING_ONLINE_LIST_OF_ALLITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_ITEM_ID, method=RequestMethod.GET)
	public String showDetailOfItems(@PathVariable(ITEM_ID) Long itemId, ModelMap model, HttpServletRequest request){
		
		model.addAttribute("item", itemService.getItemById(itemId));
		
		return SHOPPING_ONLINE_DETAIL_OF_ITEM;
	}
	
	@RequestMapping(value=REQUESTMAPPING_UPDATEITEM_ITEM_ID, method=RequestMethod.GET)
	public String updateDetailItem(@PathVariable(ITEM_ID) Long itemId, ModelMap model, HttpServletRequest request){
		
		model.addAttribute("item", itemService.getItemById(itemId));		
		model.addAttribute("updateitem", UPDATEITEM);
		request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		
		return SHOPPING_ONLINE_UPDATE_ITEM;
	}
	
	@RequestMapping(value=REQUESTMAPPING_UPDATEITEM_ITEM_ID, method=RequestMethod.POST)
	public String updateDetailItem(@PathVariable(ITEM_ID) Long itemId, @RequestParam(ITEM_NAME) String itemName, @RequestParam(ITEM_DESCRIPTION) String itemDescription, @RequestParam(ITEM_PRICE) String itemPrice, @RequestParam(ITEM_EXPIRE_DATE) String itemExpireDate, ModelMap model, HttpServletRequest request){
		
		if (itemService.modifyNameDescriptionPriceExpiredateItem(itemId , itemName, itemDescription, itemPrice, itemExpireDate)){
			
			model.addAttribute("item", itemService.getItemById(itemId));
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
				
		return REDIRECT_LISTITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_REMOVEITEM_ITEM_ID, method=RequestMethod.GET)
	public String removeDetailItem(@PathVariable(ITEM_ID) Long itemId, ModelMap model, HttpServletRequest request){
		
		if (itemService.removeItem(itemId)){
			model.addAttribute("item", null);
			request.getSession().setAttribute("items", Collections.list(Collections.enumeration(itemService.getItems().values())));
		}
		
		return REDIRECT_LISTITEMS;
	}

	public void setItemService(ItemService itemService) {
		
		this.itemService = itemService;
	}
	
	
}
