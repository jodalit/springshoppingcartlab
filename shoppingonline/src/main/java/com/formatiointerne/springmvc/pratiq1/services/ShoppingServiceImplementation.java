package com.formatiointerne.springmvc.pratiq1.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

@Service
public class ShoppingServiceImplementation implements shoppingService {
	public List<Item> basket = new LinkedList<>();
	
	@Autowired
	ItemServiceImplementation itemService;
	
	@Override
	public List<Item> getItemByName(String name) {
		System.out.println("All Items in your Basket : ");
		basket.forEach(System.out::println);
		
		return null;
	}

	@Override
	public List<Item> getAllItems() {
		System.out.println("getAllItems() calls itemService.items : ");
		itemService.items.forEach(System.out::println);
		
		System.out.println("All Items in your Basket : ");
		basket.forEach(System.out::println);
		return itemService.items;
	}

	@Override
	public void addItemToBasket(Item item) {
		basket.add(item);
		System.out.println("All Items in your Basket : ");
		basket.forEach(System.out::println);
		
		System.out.println("Basket size : " + basket.size());
	}

	@Override
	public void removeItemOnItemsListOfClient(Item item, List<Item> items) {
		items.remove(item);
		System.out.println("All availlable Items:");
		items.forEach(System.out::println);
	}	
	
}
