package com.formatiointerne.springmvc.pratiq1.services;

import java.text.DecimalFormat;
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
	public double pricetoberemoved = 0.0;
	
	@Autowired
	ItemServiceImplementation itemService;

	@Override
	public List<Item> getAllItems() {
		
		return Collections.list(Collections.enumeration(itemService.items.values())) ;
	}

	@Override
	public void addItemToBasket(Long itemId) {
		Item i = itemService.getItemById(itemId);
		
		if ( i.getPrice().doubleValue()!=price ){
			 price =i.getPrice().doubleValue();
			 totalBasket += i.getPrice().doubleValue();
			 basket.add(i);
		}
			
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
		if ( itemService.getItemById(itemId).getPrice().doubleValue()!=pricetoberemoved ){			
			pricetoberemoved = itemService.getItemById(itemId).getPrice().doubleValue();
			totalBasket = totalBasket - itemService.getItemById(itemId).getPrice().doubleValue();
			basket.remove(itemId);
		}
		
		if (totalBasket<1)
			totalBasket =0.0;
			
	}
	
	public void removeItemsToBasket() {
		basket.clear();
	}

	@Override
	public void removeItemOnItemsListOfClient(Item item, List<Item> items) {
		items.remove(item);

	}
}
