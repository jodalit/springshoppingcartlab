package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineConnexion;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ServicePerson;
import com.formatiointerne.springmvc.pratiq1.services.ServicePersonImplementation;
import com.sun.javafx.sg.prism.NGShape.Mode;
import com.sun.xml.internal.ws.policy.AssertionSet;

import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineConnexionTest {
	@Autowired 
	private ServicePerson servicePerson;
	
	@Mock
	private ServicePerson servicePersonMock;
	@Autowired
	MockHttpServletRequest request;
		
	ShoppingOnlineConnexion connexion;
 	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
		connexion = new ShoppingOnlineConnexion();
		connexion.setServicePerson(servicePersonMock);
	}
 
	 @After
	public void tearDown() throws Exception {
		 connexion=null;
		 request = null;
	}
	 
	/*@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(servicePerson);
	}
	
	@Test
	public void verifyAllMockServicesNotNull(){
		assertNotNull(servicePersonMock);
	} 
	
	@Test
	public void verifyExitance_ofUser_using_ConnexionnamePassword_providedBy_mockHttpServletRequest(){
		assertNotNull(servicePerson.getPersonByConnexionPassword(request.getParameter("personConnexion"), request.getParameter("personPassword")));
	}
	
	@Test //testGetshoppingonlinehomeclient()
	public void verifyConnexion_using_Parametres_providedBy_mockHttpServletRequest() throws Exception{ 
		if (servicePerson.getPersonByConnexionPassword(request.getParameter("personConnexion"), request.getParameter("personPassword"))!=null)
			assertTrue(true);
	}
	*/

	@Test //testGetshoppingonlinehomeclient()
	public void verifyGetshoppingonlinehomeclient_withMockServicePerson(){
	
		when(servicePersonMock.getPersonByConnexionPassword(anyString(), anyString())).thenReturn(new Person());
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
		String result = connexion.getshoppingonlinehomeclient(request.getParameter("personConnexion"), request.getParameter("personPassword"), request);
		assertNotNull(result);
		assertSame("displays super administrator session", "shoppingonlinehomesuperadmin", result);
	}
	
	@Test //testGetshoppingonlinehomeclient()
	public void verifySuccessfulAdminLogin(){
		Set<Person> persons = new HashSet<>();
		when(servicePersonMock.getPersons()).thenReturn(persons);
		Person person = new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1);
		when(servicePersonMock.getPersonByConnexionPassword("admin", "admin")).thenReturn(person);
		String result = connexion.getshoppingonlinehomeclient("admin", "admin", request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertSame("displays administrator session", "shoppingonlinehomeclient", result);
	}
	
	@Test //testGetshoppingonlinehomeclient() for invalid credentials 
	public void verifyGetshoppingonlinehomeclient_withMockServicePerson_toDisplay_InternauteSession(){
		when(servicePersonMock.getPersonByConnexionPassword(anyString(), anyString())).thenReturn(null);
		String result = connexion.getshoppingonlinehomeclient(anyString(), anyString(), request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertNotSame("displays page administrator", "shoppingonlinehomeclient", result);
		assertSame("displays internaute session", "redirect:/", result);
	}
	
	@Test //testGetshoppingonlinehomeclient() for invalid credentials 
	public void verifyWrongAdminPassword(){
		when(servicePersonMock.getPersonByConnexionPassword("admin", "super")).thenReturn(null);
		String result = connexion.getshoppingonlinehomeclient(anyString(), anyString(), request);
		System.out.println("result : " + result);
		assertNotNull(result);
		assertSame("displays internaute session", "redirect:/", result);
	}
	
	/*@Test //testGetshoppingonlinehomeclient() for invalid credentials 
	public void verifyGetshoppingonlinehomeclient_withMockServicePerson_toDisplay_InternauteSession_3(){
		Person person = new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1);
		when(servicePersonMock.getPersonByConnexionPassword("admin", "super")).thenReturn(person);
		String result = connexion.getshoppingonlinehomeclient(anyString(), anyString(), request);
		System.out.println("result : " + result);
		assertNotNull(result);
 
	}*/
	
	//TODO This feature will be enabled during Spring Security exercise 
	/*
	@Test //testGetAllPeople()
	public void verifyGetAllPeople_withMockServicePerson_returnNamePageInvoque(){
		Set<Person> persons = new HashSet<>();
		when(servicePersonMock.getPersons()).thenReturn(persons);
		ModelMap model = new ModelMap();
		String result = connexion.getAllPeople(model, request);
		assertNotNull(result);
		assertSame("shoppingonlinelistofallpeople", result);
		assertEquals("shoppingonlinelistofallpeople", result);
	}*/
}
