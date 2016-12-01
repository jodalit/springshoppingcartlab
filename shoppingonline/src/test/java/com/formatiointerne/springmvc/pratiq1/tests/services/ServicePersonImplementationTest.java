package com.formatiointerne.springmvc.pratiq1.tests.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.datamodels.Person;
import com.formatiointerne.springmvc.pratiq1.services.ServicePersonImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ServicePersonImplementationTest {
	private ServicePersonImplementation servicePerson;
	@Autowired
	MockHttpServletRequest request;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		servicePerson = new ServicePersonImplementation();
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
	}

	@After
	public void tearDown() throws Exception {
		servicePerson = null;
		request = null;
	}

	@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(servicePerson);
		assertNotNull(request);
	}

	@Test
	public void verifySetOfPeople() {
		assertNotNull(servicePerson.getPersons());
	}
	
	@Test
	public void testGetPersonByConnexionPassword_when_notEmptyStrings() {
		String connexion = "admin";
		String password = "admin";
		Person p = new Person(Long.valueOf(2), "admin", null, LocalDate.now(), null, null, connexion, password, 1);//"admin", "admin", 1);
		Person person = servicePerson.getPersonByConnexionPassword(connexion, password);
		assertNotNull(person);
		assertEquals(p, person);
		
	}
	
	@Test
	public void testGetPersonByConnexionPassword_when_anyString() {
		
		Person p = new Person(Long.valueOf(2), "admin", null, LocalDate.now(), null, null, "admin", "admin", 1);
		
		Person person = servicePerson.getPersonByConnexionPassword(anyString(), anyString());
		assertNotEquals(p, person);
		
	}
	
}
