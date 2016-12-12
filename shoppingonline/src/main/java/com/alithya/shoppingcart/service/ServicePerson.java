package com.alithya.shoppingcart.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.PersonRepository;

public interface ServicePerson {
	public int profile =0;
	public boolean getPersonByConnexionNamePassword(String connectionName, String password);
	public int getProfile();
	public void setProfile(int profile);
	public void setPersonRepository(PersonRepository personRepository);
}
