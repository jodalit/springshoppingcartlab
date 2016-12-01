package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.hamcrest.CoreMatchers.isA;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineConnexion;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
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
	private ServicePersonImplementation servicePerson;
	@Autowired
	MockHttpServletRequest request;
	

	
	@Mock
	private ServicePersonImplementation servicePersonMock;
	
	ShoppingOnlineConnexion connexion;
 	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		System.out.println("Beginning test with mocked HttpServletRequest ");
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
		connexion = new ShoppingOnlineConnexion();
	}
 
	 @After
	public void tearDown() throws Exception {
		System.out.println("End test");
	}
	 
	@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(servicePerson);
	}
	
	@Test
	public void verifyAllMockServices_areNotNull(){
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
	
	@Test
	public void verifyConnexion_withMockObject_() {
	 	Person p = new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1);
	 	 	 Set<Person> persons = new HashSet<Person>();
		persons.add(new Person(new Long(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1));
		persons.add(new Person(new Long(3), "client", null, LocalDate.now(), null, null, "client", "client", 2));
		persons.add(new Person(new Long(1), " super admin", null, LocalDate.now(), null, null, "super", "super", 0)); 
		 servicePersonMock.persons = persons;
		when(servicePersonMock.getPersonByConnexionPassword("admin", "admin")).thenReturn(p);
		String s = connexion.getshoppingonlinehomeclient("admin", "admin",  request);
		
		verify(connexion).getshoppingonlinehomeclient("admin", "admin", request);
		
	}
}
