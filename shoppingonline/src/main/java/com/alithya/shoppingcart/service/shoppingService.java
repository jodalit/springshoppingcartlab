package com.alithya.shoppingcart.service;

import java.util.List;
import java.util.Set;

import com.alithya.shoppingcart.model.Item;

public interface shoppingService {
	List<Item> getAllItems();
	void removeItemOnItemsListOfClient(Item item, List<Item> items);
	void addItemToBasket(Long itemId);
	public void removeItemsToBasket();
	public Set<Item> getBasket();
	public void setBasket(Set<Item> basket);
	public void removeItemToBasket(Long itemId);
	public boolean existItemInBasketOrNo(Long itemId);
	public double getTotalBasket();
	public void setTotalBasket(double totalBasket);
	public double getPrice();
	public void setPrice(double price);
	public double getPricetoberemoved();
	public void setPricetoberemoved(double pricetoberemoved);
	void setItemService(ItemServiceImplementation itemService);
}
