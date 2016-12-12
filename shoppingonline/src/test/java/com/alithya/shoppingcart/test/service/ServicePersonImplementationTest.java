package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.ItemRepository;
import com.alithya.shoppingcart.repository.PersonRepository;
import com.alithya.shoppingcart.service.ServicePerson;
import com.alithya.shoppingcart.service.ServicePersonImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ServicePersonImplementationTest {
	
	private ServicePerson servicePerson;
	
	@Autowired
	MockHttpServletRequest request;
	
	@Mock
	PersonRepository personRepositoryMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servicePerson = new ServicePersonImplementation();
		servicePerson.setPersonRepository(personRepositoryMock);
	}

	
	@Test
	public void testGetPersonByValidConnexionPassword() {
		
		String connexion = "admin";
		String password = "admin";
		Person p = new Person(Long.valueOf(2), "admin", null, LocalDate.now(), null, null, connexion, password, 1);
		
		List<Person> persons = new ArrayList<>();
		persons.add( p);
			
		when(personRepositoryMock.getAllPeople()).thenReturn(persons);
		
		boolean response = servicePerson.getPersonByConnexionNamePassword(connexion, password);
		
		assertTrue(response);
		
	}
	
	
	@Test
	public void testGetPersonByInvalidConnexionPassword() {
		
		String connexion = "admin";
		String password = "admin";
		Person p = new Person(Long.valueOf(2), "admin", null, LocalDate.now(), null, null, connexion, password, 1);
		
		List<Person> persons = new ArrayList<>();
		persons.add( p);
			
		when(personRepositoryMock.getAllPeople()).thenReturn(persons);
		
		boolean response = servicePerson.getPersonByConnexionNamePassword(connexion, "super");
		
		assertFalse(response);
		
	}
}
