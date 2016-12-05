package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
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
import com.alithya.shoppingcart.controller.ShoppingOnlineConnexionController;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.ServicePerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineConnexionControllerTest {
	
	@Mock
	private ServicePerson servicePersonMock;
	@Autowired
	MockHttpServletRequest request;
		
	ShoppingOnlineConnexionController connexion;
 	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		connexion = new ShoppingOnlineConnexionController();
		connexion.setServicePerson(servicePersonMock);
	}
 
	 @After
	public void tearDown() throws Exception {
		 connexion=null;
		 request = null;
	}
	
	@Test //testGetshoppingonlinehomeclient()
	public void verifyGetshoppingonlinehomeadminReturnValidPage(){
		when(servicePersonMock.getPersonByConnexionPassword(anyString(), anyString())).thenReturn(new Person());
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
		String result = connexion.getshoppingonlinehomeadmin(request.getParameter("personConnexion"), request.getParameter("personPassword"), request);
		assertNotNull(result);
		assertSame("displays super administrator session", "shoppingonlinehomesuperadmin", result);
	}
	
	@Test //testGetshoppingonlinehomeclient()
	public void verifySuccessfulAdminLogin(){
		Set<Person> persons = new HashSet<>();
		when(servicePersonMock.getPersons()).thenReturn(persons);
		Person person = new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1);
		when(servicePersonMock.getPersonByConnexionPassword("admin", "admin")).thenReturn(person);
		String result = connexion.getshoppingonlinehomeadmin("admin", "admin", request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertSame("displays administrator session", "shoppingonlinehomeclient", result);
	}
	
	@Test //testGetshoppingonlinehomeclient() for invalid credentials 
	public void verifyGetshoppingonlinehomeclientReturnValidPage(){
		when(servicePersonMock.getPersonByConnexionPassword(anyString(), anyString())).thenReturn(null);
		String result = connexion.getshoppingonlinehomeadmin(anyString(), anyString(), request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertNotSame("displays page administrator", "shoppingonlinehomeclient", result);
		assertSame("displays internaute session", "redirect:/", result);
	}
	
	@Test //testGetshoppingonlinehomeclient() for invalid credentials 
	public void verifyWrongAdminPassword(){
		when(servicePersonMock.getPersonByConnexionPassword("admin", "super")).thenReturn(null);
		String result = connexion.getshoppingonlinehomeadmin(anyString(), anyString(), request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertSame("displays internaute session", "redirect:/", result);
	}
	
}
