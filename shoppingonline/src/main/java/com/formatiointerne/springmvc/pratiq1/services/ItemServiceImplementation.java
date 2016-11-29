package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

@Service
public class ItemServiceImplementation implements ItemService {
	public Map<Long, Item> items = new HashMap<>();
	public int i=0;
	
	public ItemServiceImplementation() {

		items.put(10L, new Item(10L, "Item 1", "Item 1 kjlsddslkjds kjsdakjdsjkdskj kkdskldslksd", 16.9D));
		items.put(15L, new Item(15L, "Alithya", "Item orekj398 irewItem 1", 14.40D));
		items.put(8L, new Item(8L, "Item alithya", "Item orekj398 irewItem 1", 9.6D));
		items.put(5L, new Item(5L, "Alithya Item alithya", "Item orekj398 irewItem 1", 56.98D));
		items.put(3L, new Item(3L, "Montréeal alithya Item alithya", "Item orekj398 irewItem 1", 12.78D));
		items.put(2L, new Item(2L, "alithya Montréeal alithya item", "Item orekj398 irewItem 1", 11.25D));
		items.put(1L, new Item(1L, "item 2 Montréeal alithya", "Item orekj398 irewItem 1", 5.87D));
		items.put(4L, new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D));
		items.put(6L, new Item(6L, "Alithya Item 12 orekj398", "Item Montréllllll irewItem 1", 69.11D));
		items.put(7L, new Item(7L, "item 145", "Item orekj398 irewItem 1", 8.45D));
		items.put(9L, new Item(9L, "alithya 589 item", "Item orekj398 irewItem 1", 6.12D));
	}
	
	@Override
	public Item createItem(String id, String name, String description, String price, String expireDate) {
		 Item item = new Item(new Long(id), name, description, new Double(price), LocalDate.now());
		 items.put(Long.valueOf(id), item);
		 return item;
	}
	
	@Override
	public boolean modifyNameItem(Long id, String name) {
		Item item = this.getItemById(id);

		if (item==null)
			return false;
		
		item.setItemName(name);
		
		return true;
	}
	@Override
	public boolean modifyNameDescriptionItem(Long id, String description) {
		Item item = this.getItemById(id);

		if (item==null)
			return false;
		
		item.setDescription(description);
		
		return true;
	}
	
	@Override
	public boolean modifyPriceItem(Long id, Double price) {
		Item item = this.getItemById(id);

		if (item==null)
			return false;
		
		item.setPrice(price);
		
		return true;
	}
	
	@Override
	public boolean modifyExpiredateItem(Long id, String expireDate) {
		Item item = getItemById(id);
		if (item!=null){
			item.setExpireDate(LocalDate.now());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean modifyNameDescriptionPriceItem(Long id, String name, String description, Double price) {
		Item item = this.getItemById(id);

		if (item==null)
			return false;
		
		item.setItemName(name);
		item.setDescription(description);
		item.setPrice(price);
		
		return true;
	}

	@Override
	public boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, String price,
			String expireDate) {
		
		if (items.values().isEmpty())
			return false;
		
		items.get(id).setItemName(name);
		items.get(id).setDescription(description);
		items.get(id).setPrice(Double.valueOf(price));;
		items.get(id).setExpireDate(LocalDate.now());;
		
		return true;
	}

	@Override
	public Item getItemById(Long id) {
		return items.get(Long.valueOf(id));
	}
	
	@Override
	public Set<Item> getItemByNameDescription(String name) {
		
		Set<Item> l = new HashSet<>();
		for (Item item : items.values()) {
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
		if (items.values().isEmpty())
			return false;
		
		items.remove(id);	
		return true;
	}

	@Override
	public Long getMaxItemId() {
		Set<Long> idSet = items.keySet();
		if (idSet.isEmpty())
			return null;
		
		return Collections.max(idSet);
	}

}
