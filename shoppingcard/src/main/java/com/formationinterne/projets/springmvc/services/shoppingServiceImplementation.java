package com.formationinterne.projets.springmvc.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.formationinterne.projets.springmvc.datamodels.Item;

public class shoppingServiceImplementation implements shoppingService {
	public List<Item> items = new LinkedList<>(); //List of all Items 
	public static long numbreItem = 0;
	
	
	public shoppingServiceImplementation() {
		items.add(new Item(new Long(numbreItem++), "item1", "number 1 in the market now ...", new Double(85.5), null));
		items.add(new Item(new Long(numbreItem++), "item2", "oireoirf c...", new Double(78.5), null));
		items.add(new Item(new Long(numbreItem++), "item4", "hgdshgds ...", new Double(17.5), null));
		items.add(new Item(new Long(numbreItem++), "item5", "oierhgdf ... ghdshgds", new Double(90.05), LocalDate.of(2018, Month.MAY, 21)));
		items.add(new Item(new Long(numbreItem++), "item6", "oifdkjfd ... hdfshjfdshj ... hgdsagdsgh", new Double(7.95), LocalDate.of(2019, Month.JULY, 14)));
	}

	@Override
	public Item addItem(Long id, String name, String description, Double price, LocalDate expireDate) {
		return new Item(id, name, description, price, expireDate);
	}

	@Override
	public boolean modifyItem(Long id, String name, String description, Double price, LocalDate expireDate ) {
		Item item = this.findItemById(id);
		if (item.equals(null)){ return false;}
		
		/////
		return true;
	}

	@Override
	public boolean removeItem(Long id) {
		Item item = findItemById(id);
		if (item.equals(null)){
			return false;
		}
		
		items.remove(item);
		return true;
	}

	@Override
	public Item findItemById(Long id) {
		for (Item item : items) {
			if(item.getItemId()==id)
				return item;
		}
		return null;
	}

	@Override
	public List<Item> findItemByName(String name) {
		List<Item> lItem = new LinkedList<>();
		for (Item item : items) {
			if(item.getItemName().equals(name))
				lItem.add(item);
		}
		return lItem;
	}

	@Override
	public List<Item> findAllItems() {
		return items;
	}

}
