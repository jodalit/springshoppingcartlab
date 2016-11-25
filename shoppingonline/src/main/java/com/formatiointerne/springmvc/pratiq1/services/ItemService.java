package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface ItemService {
	Item createItem(String id, String name, String description, String price, String expireDate);
	boolean modifyNameDescriptionPriceItem(Long id, String name, String description,  Double price); //Only the mandatories fields
	//boolean modifyNameDescriptionPriceItem(Item item, String name, String description,  Double price); //Only the mandatories fields
	//boolean modifyNameDescriptionPriceExpiredateItem(Item item, String name, String description,  Double price, LocalDate expireDate); //All possible fields
	Item getItemById(Long id);
	boolean removeItem(Long id);
	Set<Item> getItemByName(String name);
	boolean modifyNameItem(Long id, String name);
	boolean modifyNameDescriptionItem(Long id, String description);
	boolean modifyPriceItem(Long id, Double price);
	boolean modifyNameDescriptionPriceExpiredateItem(Long id, String name, String description, Double price,
			LocalDate expireDate);
	boolean modifyExpiredateItem(Long id, LocalDate expireDate);
	
}
