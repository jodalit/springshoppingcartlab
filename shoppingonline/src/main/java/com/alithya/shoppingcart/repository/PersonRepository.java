package com.alithya.shoppingcart.repository;

import java.util.List;
import java.util.Set;

import com.alithya.shoppingcart.model.Person;

@FunctionalInterface
public interface PersonRepository {
//	public Person getInfoPersonConnected(String connectionName, String password);
	public List<Person> getAllPeople();
}
