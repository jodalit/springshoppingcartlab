package com.formatiointerne.springmvc.pratiq1.services;

import java.util.Map;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface ItemService {
	public Item getItemById(Long id);

	public Item createItem(String id, String name, String description, String price, String expireDate);

	public boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, String price,
			String expireDate);

	public boolean removeItem(Long id);

	public Long getMaxItemId();

	public Set<Item> getItemByNameDescription(String name);

	public void setItems(Map<Long, Item> items);

	public Map<Long, Item> getItems();

}
