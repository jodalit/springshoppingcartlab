package com.alithya.shoppingcart.repository;

import java.util.List;

import com.alithya.shoppingcart.model.Item;

public interface ItemRepository {
	
	public boolean insertItem(String itemName, String itemDescription, String itemPrice, String itemExpireDate);
	
	public boolean updateItem(Long itemId, String itemName, String itemDescription, String itemPrice, String itemExpireDate);
	
	public boolean deleteItem(Long itemId);
	
	public List<Item> getAllItems();
		
}
