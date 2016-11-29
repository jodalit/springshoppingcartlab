package com.formatiointerne.springmvc.pratiq1.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

@Service
public class ShoppingServiceImplementation implements shoppingService {
	public Set<Item> basket = new HashSet<>();
	public double totalBasket=0.0;
	public double price = 0.0;
	
	@Autowired
	ItemServiceImplementation itemService;

	@Override
	public List<Item> getAllItems() {
		System.out.println("getAllItems() calls itemService.items : ");
		itemService.items.values().forEach(System.out::println);
		
		System.out.println("All Items in your Basket : ");
		basket.forEach(System.out::println);
		return Collections.list(Collections.enumeration(itemService.items.values())) ;
	}

	@Override
	public void addItemToBasket(Long itemId) {
		Item i = itemService.getItemById(itemId);
		
		basket.add(i);
		
		if ( i.getPrice().doubleValue()!=price ){
			 price =i.getPrice().doubleValue();
			 totalBasket += i.getPrice().doubleValue();
		}
			
		System.out.println("i.getPrice().doubleValue() : " + i.getPrice());
		System.out.println("totalBasket : " + totalBasket);
	}
	
	public boolean existItemInBasketOrNo(Long itemId){
		boolean b = false;
		int i = 0;
		for (Item item : basket) {
			if (item.getItemId().equals(itemId)) {
				System.out.println("item : " + item);
				i++;
				b = true;
				break;
			}
		}
		
		return b;
	}
	
	public void removeItemToBasket(Long itemId) {
		basket.remove(itemId);
	}
	
	public void removeItemsToBasket() {
		basket.forEach(System.out::println);
		System.out.println("Basket size : " + basket.size());
		basket.clear();
		basket.forEach(System.out::println);
		System.out.println("Basket size : " + basket.size());
	}

	@Override
	public void removeItemOnItemsListOfClient(Item item, List<Item> items) {
		System.out.println("All availlable Items:");
		items.forEach(System.out::println);
		
		items.remove(item);
		System.out.println("All availlable Items:");
		items.forEach(System.out::println);
	}

}
