package com.formatiointerne.springmvc.pratiq1.tests.mock;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ServicePerson;

public class MockServicePerson implements ServicePerson {
	public Set<Person> persons = new HashSet<>();
	@Override
	public Person getPersonByConnexionPassword(String connexion, String password) {
   return  new Person(new Long(2),  "admin",  "M",  LocalDate.now(),  "200 A street",  "M");
	}

	@Override
	public Set<Person> getPersons() {
		persons.add(new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1));
	 	persons.add(new Person(new Long(3), "client", null, LocalDate.now(), null, null, "client", "client", 2));
	 	persons.add(new Person(new Long(1), " super admin", null, LocalDate.now(), null, null, "super", "super", 0)); 
	 
		return null;
	}

	@Override
	public void setPersons(Set<Person> persons) {
		
	}

}
