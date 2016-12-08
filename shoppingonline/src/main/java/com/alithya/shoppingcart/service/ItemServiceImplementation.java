package com.alithya.shoppingcart.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.IssuerSerialNumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Service
public class ItemServiceImplementation implements ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	private  Map<Long, Item> items = new HashMap<>();
	
	public int i=0;
	
	@Override
	public Map<Long, Item> itemsList(){
		
		Map<Long, Item> allItems = new HashMap<>();
		
		List<Item> listItems = itemRepository.getAllItems();
		for (Item item : listItems) {
			allItems.put(Long.valueOf( item.getItemId()), item);
		}
				
		return allItems;
	}
	
	@Override
	public Item createItem(String id, String name, String description, String price, String expireDate) {
		
		this.setItems(itemsList());
		
		Long idLong = 0L;
		try{
			idLong = (long) Integer.parseInt(id);
		}catch (NumberFormatException e) {
			e.getMessage();
			return null;
		}
				
		Double d = 0.0;
		try{
			 d = Double.parseDouble(price.trim());
		} catch (NumberFormatException e) {
			e.getMessage();
			return null;
		}
		
		Item item = new Item(Long.valueOf(id), name, description, d, LocalDate.now());
		items.put(Long.valueOf(id), item);
		return item;
	}
	

	@Override
	public boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, String price,
			String expireDate) {
		
		this.setItems(itemsList());
		
		if (items.values().isEmpty())
			return false;
		
		if (items.get(id)==null) 
			return false;
		
		items.get(id).setItemName(name);
		items.get(id).setDescription(description);
		items.get(id).setPrice(Double.valueOf(convert(price+"")));;
		items.get(id).setExpireDate(LocalDate.now());;
		
		return true;
	}

	@Override
	public Item getItemById(Long id) {
		
		this.setItems(itemsList());
		
		if (items.get(Long.valueOf(id)).getItemName().trim().isEmpty())
			return null;
		
		return items.get(Long.valueOf(id));
	}
	
	@Override
	public Set<Item> getItemByNameDescription(String name) {
		
		this.setItems(itemsList());
		
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
		this.setItems(itemsList());
		
		if (items.values().isEmpty())
			return false;
		
		if (!isNumeric(id.toString()))
			return false;
		
		System.out.println("removeItem(Long id) : " + id);
		items.remove(convertToLong(id.toString().trim()));
		
		return true;
	}

	@Override
	public Long getMaxItemId() {
		
		this.setItems(itemsList());
		
		Set<Long> idSet = items.keySet();
		if (idSet.isEmpty())
			return null;
		
		return Collections.max(idSet);
	}
	
	
	public static Double convert(String value) {
		Double d =0.0;
		try{
			 d = Double.parseDouble(value.trim());
		} catch (NumberFormatException e) {
			e.getMessage();
		}
		
		return d;
	}
	
	public static Long convertToLong(String value) {
		Long l=0L;
		try{
			 l = Long.valueOf(value.trim());
			 System.out.println("convertToLong : " + value);
		} catch (NumberFormatException e) {
			e.getMessage();
		}
		
		return l;
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    System.out.println("isNumeric : " + str);
	    
	    return true;
	}
	
	@Override
	public  Map<Long, Item> getItems() {
		return items;
	}
	
	@Override
	public void setItems(Map<Long, Item> items) {
		this.items = items;
	}
	
	@Override
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
}
