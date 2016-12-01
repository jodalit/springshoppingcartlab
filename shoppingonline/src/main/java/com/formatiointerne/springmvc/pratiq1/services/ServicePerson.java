package com.formatiointerne.springmvc.pratiq1.services;

import java.util.HashSet;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

public interface ServicePerson {
	public Person getPersonByConnexionPassword(String connexion, String password);
	public Set<Person> getPersons();
	public void setPersons(Set<Person> persons);
	public Set<Person> persons = new HashSet<>();
}
