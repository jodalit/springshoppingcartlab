package com.alithya.shoppingcart.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Item;

public class BasketServiceImplementation implements BasketService {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private Basket basketData;
	
	@Override
	public boolean addItemToBasket(Long itemId) {
		Item item = itemService.getItemById(itemId);
		
		if (basketData.getBasketItems().containsKey(itemId))
			return false;
		
		basketData.getBasketItems().put(itemId, item);
		 
		int quantity = basketData.getBasketQuantity()+1;
		basketData.setBasketQuantity(quantity);
		 
		Double totalAmount = basketData.getBasketTotalAmount() + item.getPrice();
		basketData.setBasketTotalAmount(totalAmount);
		 
		return true;
	}

	/*@Override
	public boolean verifyItemBasket(Long itemId) {
		
		if (basketData.getBasketItems().containsKey(itemId))
			return true;
		
		return false;
	}*/

	@Override
	public boolean removeItemToBasket(Long itemId) {
		
		if (basketData.getBasketItems().containsKey(itemId)){
			Double priceRemovedItem  = basketData.getBasketItems().get(itemId).getPrice(); 

			basketData.getBasketItems().remove(itemId, basketData.getBasketItems().get(itemId));
			
			Double totalAmount = basketData.getBasketTotalAmount()-priceRemovedItem;
			basketData.setBasketTotalAmount(totalAmount);
			
			int quantity = basketData.getBasketQuantity()-1;
			basketData.setBasketQuantity(quantity);
			 
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeItemsToBasket() {
		if (basketData.getBasketItems().isEmpty())
			return false;
		
		basketData.getBasketItems().clear();
		basketData.setBasketQuantity(0);
		basketData.setBasketTotalAmount(0.0);
		
		return true;
	}

	@Override
	public boolean removeItemOnItemsListOfClient(Item item, List<Item> items) {
		Long itemId = item.getItemId();
		
		return basketData.getBasketItems().remove(itemId, item);
	}

	@Override
	public Map<Long, Item> getItemsBasket() {
		return basketData.getBasketItems();
	}
	
	@Override
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Override
	public void setBasketData(Basket basketData) {
		this.basketData = basketData;
	}
	
	
}
