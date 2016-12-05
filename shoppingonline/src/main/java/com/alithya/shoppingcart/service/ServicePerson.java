package com.alithya.shoppingcart.service;

import java.util.HashSet;
import java.util.Set;

import com.alithya.shoppingcart.model.Person;

public interface ServicePerson {
	public Person getPersonByConnexionPassword(String connexion, String password);
	public Set<Person> getPersons();
	public void setPersons(Set<Person> persons);
	public Set<Person> persons = new HashSet<>();
}
