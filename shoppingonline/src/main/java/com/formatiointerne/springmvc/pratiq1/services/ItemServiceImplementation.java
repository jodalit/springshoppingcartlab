package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

@Service
public class ItemServiceImplementation implements ItemService {
	public List<Item> items = new LinkedList<>();
	
	public ItemServiceImplementation() {
		items.add(new Item(new Long(10), "Item 1", "Item 1 kjlsddslkjds kjsdakjdsjkdskj kkdskldslksd", new Double(12.3)));
		items.add(new Item(new Long(2), "alithya", "Item orekj398 irew", new Double(21.46)));
		items.add(new Item(new Long(15), "iuejkfdjhsfd ", "-043lkewdkljd jkfdjkfdjk ", new Double(8.97)));
		items.add(new Item(new Long(8), "Item alithya", "uyerjhdfnmfd okjfdkmfdkj jkuydfsudfsjh jkfdjkdfkj", new Double(31.25)));
		items.add(new Item(new Long(25), "alithya Item 1", "98rekjmn jkfdjfdsiu kfdkfdkj", new Double(11.99)));
		items.add(new Item(new Long(17), "Item 1 alithya", "87dfkjfdskjds lkjfdsiuofdsoim, kmjvc", new Double(56.8)));
		items.add(new Item(new Long(6), "alithya Item", "98fdkjfdui lkiofdm,fd  lkfdlifd", new Double(9.65)));
		items.add(new Item(new Long(87), "alithya montreal", "iufdnm kjfdskj832  434jhfdnmkj -0", new Double(15.12)));
		items.add(new Item(new Long(17), "Item 1", "iufdnm kjfdskj832  434jhfdnmkj -0", new Double(36.25)));
		items.forEach(System.out::println);
	}
	
	@Override
	public Item createItem(Long id, String name, String description, Double price, LocalDate expireDate) {
		
		return null;
	}

	@Override
	public boolean modifyNameDescriptionPriceItem(Long id, String name, String description, Double price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyNameDescriptionPriceItem(Long id, String name, String description, Double price,
			LocalDate expireDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyNameDescriptionPriceItem(Item item, String name, String description, Double price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyNameDescriptionPriceItem(Item item, String name, String description, Double price,
			LocalDate expireDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item getItemById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Item> getItemByName(String name) {
		List<Item> l = new LinkedList<>();
		for (Item item : items) {
			if (item.getItemName().equals(name) || item.getItemName()==name){
				l.add(item);
			}
		}
		l.forEach(System.out::println);
		return l;
	}
	
	@Override
	public boolean removeItem(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
