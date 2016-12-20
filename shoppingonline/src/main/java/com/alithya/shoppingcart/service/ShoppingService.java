package com.alithya.shoppingcart.service;

import java.util.List;
import java.util.Set;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

public interface ShoppingService {
	public List<Item> getAllItems();

	void setItemRepository(ItemRepository itemRepository);
}
