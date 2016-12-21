package com.alithya.shoppingcart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Service
public class ShoppingServiceImplementation implements shoppingService {
	private Set<Item> basket = new HashSet<>();
	private double totalBasket=0.0;
	private double price = 0.0;
	private double pricetoberemoved = 0.0;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	ItemServiceImplementation itemService;

	@Override
	public List<Item> getAllItems() {
		return itemRepository.getAllItems() ; 
	}

	@Override
	public boolean addItemToBasket(Long itemId) {
		Item i = itemService.getItemById(itemId);
		
		if ( i.getPrice().doubleValue()!=price ){
			 price =i.getPrice().doubleValue();
			 totalBasket += i.getPrice().doubleValue();
			 basket.add(i);
			 return true;
		}
		
		return false;
	}
	
	@Override
	public boolean existItemInBasketOrNo(Long itemId){
		boolean b = false;
		Set<Item> basket = this.getBasket();
		
		for (Item item : basket) {
			if (item.getItemId().equals(itemId)) {
				System.out.println("item : " + item);
				b = true;
				break;
			}
		}
		
		return b;
	}
	
	@Override
	public boolean removeItemToBasket(Long itemId) {
		if ( itemService.getItemById(itemId).getPrice().doubleValue()!=pricetoberemoved ){			
			pricetoberemoved = itemService.getItemById(itemId).getPrice().doubleValue();
			totalBasket = totalBasket - itemService.getItemById(itemId).getPrice().doubleValue();
			basket.remove(itemId);
			
			if (totalBasket<1)
				totalBasket =0.0;
			
			return true;
		}
		
		return false;	
	}
	
	@Override
	public boolean removeItemsToBasket() {
		if (this.getBasket().isEmpty())
			return false;
		
		getBasket().clear();
		return true;
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
	
	@Override
	public void setItemService(ItemServiceImplementation itemService) {
		this.itemService = itemService;
	}
	
	
}
