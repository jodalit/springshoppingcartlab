package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;

public interface ItemService {
	Item createItem(Long id, String name, String description, Double price, LocalDate expireDate);
	boolean modifyNameDescriptionPriceItem(Long id, String name, String description,  Double price); //Only the mandatories fields
	boolean modifyNameDescriptionPriceItem(Long id, String name, String description,  Double price, LocalDate expireDate); //All possible fields
	boolean modifyNameDescriptionPriceItem(Item item, String name, String description,  Double price); //Only the mandatories fields
	boolean modifyNameDescriptionPriceItem(Item item, String name, String description,  Double price, LocalDate expireDate); //All possible fields
	Item getItemById(Long id);
	boolean removeItem(Long id);
	Set<Item> getItemByName(String name);
}
