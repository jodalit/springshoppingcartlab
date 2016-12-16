package com.alithya.shoppingcart.service;

import java.util.List;
import java.util.Set;

import com.alithya.shoppingcart.model.Item;

@FunctionalInterface
public interface ShoppingService {
	public List<Item> getAllItems();
}
