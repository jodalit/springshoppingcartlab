package com.formatiointerne.springmvc.pratiq1.services;

import java.util.List;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface shoppingService {
	//Set<Item> getItemByName(String name);
	Set<Item> getAllItems();
	//void addItemToBasket(Item item);
	//void removeItemOnItemsListOfClient(Long itemId);
	void removeItemOnItemsListOfClient(Item item, List<Item> items);
	void addItemToBasket(Long itemId);
}
