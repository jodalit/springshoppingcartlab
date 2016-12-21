package com.alithya.shoppingcart.repository;

import java.util.List;
import com.alithya.shoppingcart.model.Person;

@FunctionalInterface
public interface PersonRepository {
	public List<Person> getAllPeople();
}
