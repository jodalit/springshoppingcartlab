package com.formatiointerne.springmvc.pratiq1.services;

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
	//public List<Item> basket = new LinkedList<>();
	public Set<Item> basket = new HashSet<>();
	
	@Autowired
	ItemServiceImplementation itemService;

	@Override
	public Set<Item> getAllItems() {
		System.out.println("getAllItems() calls itemService.items : ");
		itemService.items.values().forEach(System.out::println);
		
		System.out.println("All Items in your Basket : ");
		basket.forEach(System.out::println);
		return (Set<Item>) itemService.items.values();
	}

	@Override
	public void addItemToBasket(Long itemId) {
		System.out.println("existItemInBasketOrNo(itemId) : " + existItemInBasketOrNo(itemId));
		//if (!existItemInBasketOrNo(itemId)){
			Item i = itemService.getItemById(itemId);
			basket.add(i);
			System.out.println("All Items in your Basket : ");
			basket.forEach(System.out::println);
			System.out.println("Basket size : " + basket.size());
		//}
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
