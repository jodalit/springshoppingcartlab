package com.alithya.shoppingcart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Service
public class ShoppingServiceImplementation implements ShoppingService {	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> getAllItems() {
		return itemRepository.getAllItems() ; 
	}
	
}
