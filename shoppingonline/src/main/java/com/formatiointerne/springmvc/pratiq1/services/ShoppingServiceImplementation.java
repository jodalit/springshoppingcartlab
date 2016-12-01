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
	//public Set<Item> basket = new HashSet<>();
	private Set<Item> basket = new HashSet<>();
	private double totalBasket=0.0;
	private double price = 0.0;
	private double pricetoberemoved = 0.0;
	
	@Autowired
	ItemServiceImplementation itemService;

	@Override
	public List<Item> getAllItems() {
		return Collections.list(Collections.enumeration(itemService.getItems().values())) ;
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
	
	@Override
	public boolean existItemInBasketOrNo(Long itemId){
		boolean b = false;
		int i = 0;
		Set<Item> basket = this.getBasket();
		
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
	
	@Override
	public void removeItemToBasket(Long itemId) {
		if ( itemService.getItemById(itemId).getPrice().doubleValue()!=pricetoberemoved ){			
			pricetoberemoved = itemService.getItemById(itemId).getPrice().doubleValue();
			totalBasket = totalBasket - itemService.getItemById(itemId).getPrice().doubleValue();
			basket.remove(itemId);
		}
		
		if (totalBasket<1)
			totalBasket =0.0;
			
	}
	
	@Override
	public void removeItemsToBasket() {
		basket.clear();
	}

	@Override
	public void removeItemOnItemsListOfClient(Item item, List<Item> items) {
		items.remove(item);

	}
	
	@Override
	public Set<Item> getBasket() {
		return basket;
	}
	
	@Override
	public void setBasket(Set<Item> basket) {
		this.basket = basket;
	}
	
	@Override
	public double getTotalBasket() {
		return totalBasket;
	}
	
	@Override
	public void setTotalBasket(double totalBasket) {
		this.totalBasket = totalBasket;
	}
	
	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public double getPricetoberemoved() {
		return pricetoberemoved;
	}
	
	@Override
	public void setPricetoberemoved(double pricetoberemoved) {
		this.pricetoberemoved = pricetoberemoved;
	}
	
}
