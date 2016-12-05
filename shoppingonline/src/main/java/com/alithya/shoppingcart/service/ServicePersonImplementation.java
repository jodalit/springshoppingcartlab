package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

@Service
public  class ServicePersonImplementation implements ServicePerson {
	private Set<Person> persons = new HashSet<>();
	
	public ServicePersonImplementation() {
		fillPersons();
	}

	public void fillPersons(){
		persons.add(new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1));
		persons.add(new Person(new Long(3), "client", null, LocalDate.now(), null, null, "client", "client", 2));
		persons.add(new Person(new Long(1), " super admin", null, LocalDate.now(), null, null, "super", "super", 0));
	}
	
	@Override
	public Person getPersonByConnexionPassword(String connexion, String password) {
		for (Person person : persons) {
			if (person.getConnexionname().equals(connexion) && person.getPassword().equals(password)){
				return person;
			}
		}
		return null;
	}

	@Override
	public Set<Person> getPersons() {
		return persons;
	}

	@Override
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	
}
