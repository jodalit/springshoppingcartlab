package com.formatiointerne.springmvc.pratiq1.services;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;

@Service
public class ServicePersonImplementation implements ServicePerson {
	public List<Person> persons = new LinkedList<>();
	
	
	public ServicePersonImplementation() {
		persons.add(new Person(new Long(1), "admin", null, LocalDate.now(), null, null, "admin", "admin", 0));
		System.out.println(persons);
	}


	@Override
	public Person getClientByConnexionPassword(String connexion, String password) {
		for (Person person : persons) {
			if (person.getConnexionname().equals(connexion) && person.getPassword().equals(password)){
				return person;
			}
		}
		return new Person();
	}

}
