package com.formatiointerne.springmvc.pratiq1.services;

import java.util.List;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface shoppingService {
	List<Item> getAllItems();
	void removeItemOnItemsListOfClient(Item item, List<Item> items);
	void addItemToBasket(Long itemId);
}
