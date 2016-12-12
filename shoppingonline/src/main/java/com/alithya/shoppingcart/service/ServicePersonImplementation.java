package com.alithya.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.PersonRepository;

@Service
public  class ServicePersonImplementation implements ServicePerson {
	private int profile;
	 
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public boolean getPersonByConnexionNamePassword(String connectionName, String password) {
		
		boolean response = false; 
		
		for (Person person : personRepository.getAllPeople()) {
			if (person.getPersonConnectionName().equals(connectionName) && person.getPersonPassword().equals(password)){
				response = true;
				setProfile(person.getProfile());
				break;
			}
		}
		
		return response;
	}
	
	@Override
	public int getProfile() {
		return profile;
	}
	
	@Override
	public void setProfile(int profile) {
		this.profile = profile;
	}
}
