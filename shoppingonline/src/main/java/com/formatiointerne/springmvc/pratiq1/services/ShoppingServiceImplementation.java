package com.formatiointerne.springmvc.pratiq1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

@Service
public class ShoppingServiceImplementation implements shoppingService {
	@Autowired
	ItemServiceImplementation itemService;
	
	@Override
	public List<Item> getItemByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllItems() {
		System.out.println("getAllItems() calls itemService.items : ");
		itemService.items.forEach(System.out::println);
		return itemService.items;
	}	

}
