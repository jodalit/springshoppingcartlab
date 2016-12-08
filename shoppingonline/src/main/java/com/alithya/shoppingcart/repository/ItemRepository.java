package com.alithya.shoppingcart.repository;

import java.util.List;

import com.alithya.shoppingcart.model.Item;

public interface ItemRepository {
	/*
	 public Item createItem(String id, String name, String description, String price, String expireDate);
	
	public Item getItemById(Long id);
	*/
	public boolean updateItem(Long itemId, String itemName, String itemDescription, String itemPrice, String itemExpireDate);
	public List<Item> getAllItems();
}
