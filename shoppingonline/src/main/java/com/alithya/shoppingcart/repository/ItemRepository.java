package com.alithya.shoppingcart.repository;

import java.util.List;

import com.alithya.shoppingcart.model.Item;

public interface ItemRepository {
	List<Item> getAllItems();
}
