package com.alithya.shoppingcart.service;

import java.util.List;
import java.util.Map;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Item;

public interface BasketService {
	//public boolean addItemToBasket(Long itemId);
	public Basket addItemToBasket(Long itemId);
	//public boolean verifyItemBasket(Long itemId);
	public boolean removeItemToBasket(Long itemId);
	public boolean removeItemsToBasket();
	public boolean removeItemOnItemsListOfClient(Item item, List<Item> items);
	public Map<Long, Item> getItemsBasket();
	public Double getTotalAmountBasket();
	public Map<Integer, Object> initBasket();
	public void setItemService(ItemService itemService);

	public void setBasketData(Basket basketData);
	public Basket getBasketData();
}
