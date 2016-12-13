package com.alithya.shoppingcart.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

public class ItemCachedSingleton {
	
	@Autowired
	private ItemRepository itemRepository;
	
	//private  Map<Long, Item> items = new HashMap<>();
	
	private static ItemCachedSingleton allItems = new ItemCachedSingleton();
	
	public Map<Long, Item> itemsList(){
		
		Map<Long, Item> allItems = new HashMap<>();
		
		List<Item> listItems = itemRepository.getAllItems();
		for (Item item : listItems) {
			allItems.put(Long.valueOf( item.getItemId()), item);
		}
				
		return allItems;
	}
	
	
	/*
	public Map<Long, Item> getItems() {
		return items;
	}



	public void setItems(Map<Long, Item> items) {
		this.items = items;
	}

	*/

	public ItemRepository getItemRepository() {
		return itemRepository;
	}



	public static ItemCachedSingleton getAllItems() {
		return allItems;
	}



	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public static void setAllItems(ItemCachedSingleton allItems) {
		ItemCachedSingleton.allItems = allItems;
	}
	
	
	
	
}
