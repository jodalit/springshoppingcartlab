package com.alithya.shoppingcart.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Service
public class ItemServiceImplementation implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	//private  Map<Long, Item> items = new HashMap<>();
	
	@Override
	public Map<Long, Item> getItemsList() {
		
		Map<Long, Item> allItems = new HashMap<>();
		
		List<Item> listItems = itemRepository.getAllItems();
		for (Item item : listItems) {
			allItems.put(Long.valueOf( item.getItemId()), item);
		}
				
		return allItems;
	}
	
	@Override
	public Long createItem(String name, String description, String price, String expireDate) {
		
		if (itemRepository.insertItem(name, description, price, expireDate)){
			return this.getMaxItemId();
		}
			
		return 0L;
	}
	
	@Override
	public boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, String price,
			String expireDate) {
		
		if (this.getItemsList().values().isEmpty())
			return false;
		
		if (!this.getItemsList().containsKey(id)) 
			return false;
		
		if (itemRepository.updateItem(id, name, description, price, expireDate)){
			//this.setItems(itemsList());
			this.getItemsList();
			return true;
		}
	
		return false;
	}

	@Override
	public Item getItemById(Long id) {

		if (this.getItemsList().get(Long.valueOf(id)).getItemName().trim().isEmpty())
			return null;
		
		return this.getItemsList().get(Long.valueOf(id));
	}
	
	@Override
	public Set<Item> getItemByNameDescription(String name) {
		
		Set<Item> l = new HashSet<>();
		for (Item item : this.getItemsList().values()) {
			if (item.getDescription().toLowerCase().contains(name.trim().toLowerCase()) ){
				l.add(item);
			} else if (item.getItemName().toLowerCase().contains(name.trim().toLowerCase())) {
				l.add(item);
			} 
		}
		
		return l;
	}
	
	@Override
	public boolean removeItem(Long id) {
				
		if (this.getItemsList().values().isEmpty())
			return false;
		
		if (!this.getItemsList().containsKey(id)) 
			return false;
		
		if (itemRepository.deleteItem(id)){
			//this.setItems(itemsList());
			this.getItemsList();
			return true;
		}
		
		return false;
	}

	@Override
	public Long getMaxItemId() {
		//this.setItems(itemsList());
		
		Set<Long> idSet = this.getItemsList().keySet();
		if (idSet.isEmpty())
			return null;
		
		return Collections.max(idSet);
	}
	
	/*
	@Override
	public  Map<Long, Item> getItems() {
		return items;
	}
	
	@Override
	public void setItems(Map<Long, Item> items) {
		this.items = items;
	}
	*/
	
	@Override
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
}
