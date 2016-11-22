package com.formatiointerne.springmvc.pratiq1.services;

import java.util.List;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface shoppingService {
	List<Item> getItemByName(String name);
	List<Item> getAllItems();
}
