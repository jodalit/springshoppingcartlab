package com.alithya.shoppingcart.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.alithya.shoppingcart.service.ItemService;

@Controller
@RequestMapping(ShoppingOnLineItemController.RESQUESTMAPPING_ADMIN)
public class ShoppingOnLineItemController {
	
	public static final String RESQUESTMAPPING_ADMIN = "admin";
	public static final String MODEL_ITEMS = "items";
	public static final String MODEL_UPDATE_ITEM = "updateitem";
	public static final String MODEL_ITEM = "item";
	public static final String MODEL_ALL_ITEMS = "allitems";
	public static final String MODEL_NEW_ITEM_ID = "Itemnumero";
	public static final String MODEL_SAVE_RESULT = "saveresult";
	public static final String MODEL_ADD_NEW_ITEM = "addnewitem";
	public static final String REQUESTMAPPING_REMOVEITEM_ITEM_ID = "/removeitem/{itemId}";
	public static final String REQUESTMAPPING_UPDATEITEM_ITEM_ID = "/updateitem/{itemId}";
	public static final String REQUESTMAPPING_ITEM_ID = "/item/{itemId}";
	public static final String REQUESTMAPPING_LIST_ITEMS = "/item/listitems";
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
	public static final String ADD_NEW_ITEM ="Add New Item";
	public static final String ALL_ITEMS ="List of all items";
	public static final String UPDATE_ITEM ="Updating Item : ";
	public static final String SAVE_RESULT ="Item : ";
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value=REQUESTMAPPING_ADD_ITEM, method=RequestMethod.GET)
	public String getAddItem(ModelMap model){
		
		model.addAttribute(MODEL_ADD_NEW_ITEM, ADD_NEW_ITEM);
		model.addAttribute(MODEL_SAVE_RESULT, null);
		
		return SHOPPING_ONLINE_NEWITEM;
	}
	
	@RequestMapping(value=REQUESTMAPPING_SAVE_ITEM, method=RequestMethod.POST)
	public String getSaveItem(ModelMap model,@RequestParam(ITEM_NAME) String itemName, @RequestParam(ITEM_DESCRIPTION) String itemDescription, @RequestParam(ITEM_PRICE) String itemPrice, @RequestParam(ITEM_EXPIRE_DATE) String itemExpireDate, HttpServletRequest request){
		
		Long newItemId = itemService.createItem(itemName, itemDescription, itemPrice, itemExpireDate);
		if (newItemId == 0L)
			return SHOPPING_ONLINE_NEWITEM;
		
		model.addAttribute(MODEL_SAVE_RESULT, SAVE_RESULT);
		model.addAttribute(MODEL_NEW_ITEM_ID, newItemId);
		request.getSession().setAttribute(MODEL_ITEMS, Collections.list(Collections.enumeration(itemService.getItemsList().values())));
		
		return SHOPPING_ONLINE_LIST_OF_ALLITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_LIST_ITEMS, method=RequestMethod.GET)
	public String getAllItems(ModelMap model, HttpServletRequest request){
		
		model.addAttribute(MODEL_ALL_ITEMS, ALL_ITEMS);
	
		return SHOPPING_ONLINE_LIST_OF_ALLITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_UPDATEITEM_ITEM_ID, method=RequestMethod.GET)
	public String updateDetailItem(@PathVariable(ITEM_ID) Long itemId, ModelMap model, HttpServletRequest request){
		
		model.addAttribute(MODEL_ITEM, itemService.getItemById(itemId));		
		model.addAttribute(MODEL_UPDATE_ITEM, UPDATE_ITEM);
		request.getSession().setAttribute(MODEL_ITEMS, Collections.list(Collections.enumeration(itemService.getItemsList().values())));
		
		return SHOPPING_ONLINE_UPDATE_ITEM;
	}
	
	@RequestMapping(value=REQUESTMAPPING_UPDATEITEM_ITEM_ID, method=RequestMethod.POST)
	public String updateDetailItem(@PathVariable(ITEM_ID) Long itemId, @RequestParam(ITEM_NAME) String itemName, @RequestParam(ITEM_DESCRIPTION) String itemDescription, @RequestParam(ITEM_PRICE) String itemPrice, @RequestParam(ITEM_EXPIRE_DATE) String itemExpireDate, ModelMap model, HttpServletRequest request){
		
		if (itemService.modifyNameDescriptionPriceExpiredateItem(itemId , itemName, itemDescription, itemPrice, itemExpireDate)){
			
			model.addAttribute(MODEL_ITEM, itemService.getItemById(itemId));
			request.getSession().setAttribute(MODEL_ITEMS, Collections.list(Collections.enumeration(itemService.getItemsList().values())));
		}
				
		return REDIRECT_LISTITEMS;
	}
	
	@RequestMapping(value=REQUESTMAPPING_REMOVEITEM_ITEM_ID, method=RequestMethod.GET)
	public String removeDetailItem(@PathVariable(ITEM_ID) Long itemId, ModelMap model, HttpServletRequest request){
		
		if (itemService.removeItem(itemId)){
			model.addAttribute(MODEL_ITEM, null);
			request.getSession().setAttribute(MODEL_ITEMS, Collections.list(Collections.enumeration(itemService.getItemsList().values())));
		}
		
		return REDIRECT_LISTITEMS;
	}

	public void setItemService(ItemService itemService) {
		
		this.itemService = itemService;
	}
	
	
}
