package com.alithya.shoppingcart.service;

import java.util.Map;
import java.util.Set;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

public interface ItemService {
	public Item getItemById(Long id);

	public Long createItem(String name, String description, String price, String expireDate);

	public boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, String price,
			String expireDate);

	public boolean removeItem(Long id);

	public Long getMaxItemId();

	public Set<Item> getItemByNameDescription(String name);

	//public void setItems(Map<Long, Item> items);

	//public Map<Long, Item> getItems();
	
	public Map<Long, Item> getItemsList();
	
	void setItemRepository(ItemRepository itemRepository);
	
}
