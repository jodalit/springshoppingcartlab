package com.formationinterne.projets.springmvc.services;

import java.time.LocalDate;
import java.util.List;

import com.formationinterne.projets.springmvc.datamodels.Item;

public interface shoppingService {
	Item addItem(Long id, String name, String description, Double price, LocalDate expireDate);
	boolean modifyItem(Long id, String name, String description, Double price, LocalDate expireDate);
	boolean removeItem(Long id);
	Item findItemById(Long id);
	List<Item> findItemByName(String name);
	List<Item> findAllItems();
}
