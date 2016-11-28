package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
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
	public Set<Item> itemsOld = new HashSet<>();
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
		/*items.add(new Item(new Long(10), "Item 1", "Item 1 kjlsddslkjds kjsdakjdsjkdskj kkdskldslksd", new Double(12.3)));
		items.add(new Item(new Long(2), "alithya", "Item orekj398 irewItem 1", new Double(21.46)));
		items.add(new Item(new Long(15), "iuejkfdjhsfd ", "-043lkewdkljd jkfdjkfdjk ", new Double(8.97)));
		items.add(new Item(new Long(8), "Item alithya", "uyerjhdfnmfd okjfdkmfdkj jkuydfsudfsjh jkfdjkdfkj", new Double(31.25)));
		items.add(new Item(new Long(25), "alithya Item 2", "98rekjmn jkfdjfdsiu alithya kfdkfdkj", new Double(11.99)));
		items.add(new Item(new Long(17), "Item 3 alithya", "87dfkjfdskjds lkjfdsiuofdsoim, kmjvc", new Double(56.8)));
		items.add(new Item(new Long(6), "alithya Item", "98fdkjfdui lkiofdm,fd  lkfdlifd", new Double(9.65)));
		items.add(new Item(new Long(87), "alithya montreal", "iufdnm kjfdskj832  434jhfdnmkj -0", new Double(15.12)));
		items.add(new Item(new Long(17), "Item 4", "iufdnm kjfdskj832  alithya 434jhfdnmkj -0", new Double(36.25)));
		items.forEach(System.out::println);*/
	}
	
	@Override
	public Item createItem(String id, String name, String description, String price, String expireDate) {
		 Item item = new Item(new Long(id), name, description, new Double(price), LocalDate.now());
		 items.put(Long.valueOf(id), item);
		 System.out.println("item ;;;;;;;" +item);
		 System.out.println("Collection<Item> setItems");
		 Collection<Item> setItems = items.values();
		 setItems.forEach(System.out::println);
		 
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
		
		/*
		Iterator<Item> i = items.iterator();
		
		while(i.hasNext())
		{
			 Item item = i.next();
			 if (item.getItemId().equals(id)){
				 	System.out.println("Before update : "+item);
					item.setItemName(name);
					item.setDescription(description);
					item.setPrice(new Double(price) );
					item.setExpireDate(LocalDate.now());//expireDate);
					
					System.out.println();
					
					System.out.println("Before update : "+item);
					return true;
				}
			
		}
		*/
		System.out.println("=========== UPDATE : BEFORE ===========");
		System.out.println(items.get(id));
		System.out.println();
		
		items.get(id).setItemName(name);
		items.get(id).setDescription(description);
		items.get(id).setPrice(Double.valueOf(price));;
		items.get(id).setExpireDate(LocalDate.now());;
		
		System.out.println();
		System.out.println("=========== UPDATE : AFTER ===========");
		System.out.println(items.get(id));
		
		return true;
	}

	@Override
	public Item getItemById(Long id) {
		/*
		Iterator<Item> iterator = items.values().iterator();
		
		while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			if (item.getItemId().equals(id))
				return item;
			
		}
				
		return null;
		*/
		System.out.println("---------items.get(id) -----");
		System.out.println(items.get(id));
		
		return items.get(Long.valueOf(id));
	}
	
	@Override
	public Set<Item> getItemByName(String name) {
		
		Set<Item> l = new HashSet<>();
		for (Item item : items.values()) {
			if (item.getDescription().toLowerCase().contains(name.trim().toLowerCase()) ){
				l.add(item);
			} else if (item.getItemName().toLowerCase().contains(name.trim().toLowerCase())) {
				l.add(item);
			} 
		}
		l.forEach(System.out::println);
		return l;
	}
	
	@Override
	public boolean removeItem(Long id) {
		//Iterator<Item> iterator = items.values().iterator();
		//System.out.println("items size before removing is:"+items.size());
		if (items.values().isEmpty())
			return false;
		
		System.out.println("*************** REMOVE : BEFORE ***************");
		items.values().forEach(System.out::println);
		System.out.println();
		
		items.remove(id);
		
		System.out.println();
		System.out.println("*************** REMOVE : AFTER ***************");
		items.values().forEach(System.out::println);
		/*while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			if (item.getItemId().equals(id)){
				System.out.println("!!!!inside!!!");
				
				items.remove(item.getItemId());
				System.out.println("items size after removing is:"+items.size());
				return true;
			}
			else
				System.out.println("!!!!outside!!!");
		}*/
		
		return true;
	}

}
