package com.alithya.shoppingcart.service;

import com.alithya.shoppingcart.repository.PersonRepository;

public interface ServicePerson {
	public int profile =0;
	public boolean getPersonByConnexionNamePassword(String connectionName, String password);
	public int getProfile();
	public void setProfile(int profile);
	public void setPersonRepository(PersonRepository personRepository);
}
