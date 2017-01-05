package com.alithya.shoppingcart.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Item;

@Service
public class BasketServiceImplementation implements BasketService {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private FinancialService customerService;
	
	private Basket basketData = new Basket();
		
	@Override
	public Basket addItemToBasket(Long itemId) {
		Item item = itemService.getItemById(itemId);
			
		Map<Long, Item> items = new HashMap<>();
		if(basketData.getBasketItems() != null){
			if (basketData.getBasketItems().containsKey(itemId))
				return basketData;
			
			items = basketData.getBasketItems();
		} 	
		
		items.put(itemId, item);
		basketData.setBasketItems(items);
		 
		basketData.setBasketQuantity(items.size());
		
		double totalAmount;
		if(basketData.getBasketTotalAmount()== null){
			totalAmount = item.getPrice().doubleValue();
		} else {
			totalAmount = basketData.getBasketTotalAmount().doubleValue() + item.getPrice().doubleValue();
		}
		basketData.setBasketTotalAmount(Double.valueOf(totalAmount));
		
		basketData.setCustomer(customerService.getCustomerInfo());
		basketData.setBasketDate(LocalDate.now());
		
		if (basketData.getBasketReference()==null){
			String basketReference = new StringBuilder()
					.append(customerService.getCustomerInfo().getCustomerId().toString())
					.append("-")
					.append(System.currentTimeMillis()).toString();
			
			basketData.setBasketReference(basketReference);
		}
				
		return basketData;
	}
	
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
		if (basketData.getBasketItems()==null)
			return false;
		
		basketData.setBasketItems(null);
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
	public Double getTotalAmountBasket() {
		return basketData.getBasketTotalAmount();
	}
	
	@Override
	public Map<Integer, Object> initBasket(){
		Map<Integer, Object> emptyBasket = new HashMap<>();
		
		emptyBasket.put(1, null);
		emptyBasket.put(2, 0);
		emptyBasket.put(3, 0.0);
		
		return emptyBasket;
	}
	
	@Override
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Override
	public void setBasketData(Basket basketData) {
		this.basketData = basketData;
	}

	@Override
	public Basket getBasketData() {
		return basketData;
	}
	
}
