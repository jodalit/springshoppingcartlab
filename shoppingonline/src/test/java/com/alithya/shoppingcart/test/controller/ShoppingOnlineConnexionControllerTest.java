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
	
	public static final String MESSAGE_ADMINISTRATOR_CONNECTED = "Administrator connected";
	public static final String MESSAGE_USER_NOT_CONNECTED = "User not connected";

	@Mock
	private ServicePerson servicePersonMock;
	
	@Autowired
	MockHttpServletRequest request;
		
	ShoppingOnlineConnexionController connectionController;
 	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		connectionController = new ShoppingOnlineConnexionController();
		connectionController.setServicePerson(servicePersonMock);
	}
 
	@Test 
	public void verifyGetShoppingOnLineHomeAdmin_ReturnValidPage(){
		
		when(servicePersonMock.getPersonByConnexionNamePassword("admin", "admin")).thenReturn(true);
		when(servicePersonMock.getProfile()).thenReturn(1);
		
		request.setParameter("personConnexion", "admin");
		request.setParameter("personPassword", "admin");
		String result = connectionController.getShoppingOnLineHomeAdmin("admin", "admin", request);
		
		assertNotNull(result);
		assertSame(1, connectionController.USER_PROFILE);
		assertNotNull(request.getSession().getAttribute(connectionController.MODEL_NAME_CONNECTION_NAME));
		assertSame(MESSAGE_ADMINISTRATOR_CONNECTED, connectionController.SHOPPING_ONLINE_HOME_ADMIN, result);
	}
	
	@Test  
	public void verifyGetShoppingOnLineHomeAdmin_ReturnInValidPage(){
		
		when(servicePersonMock.getPersonByConnexionNamePassword(anyString(), anyString())).thenReturn(false);
		when(servicePersonMock.getProfile()).thenReturn(0);
		
		String result = connectionController.getShoppingOnLineHomeAdmin(anyString(), anyString(), request);
		
		assertNotNull(result);
		assertNotSame(connectionController.SHOPPING_ONLINE_HOME_ADMIN, result);
		assertSame(MESSAGE_USER_NOT_CONNECTED, connectionController.REDIRECT, result);
	}
	
	@Test  
	public void verifyGetShoppingOnLineHomeAdmin_WrongConnectionNameAndPassword(){
		
		when(servicePersonMock.getPersonByConnexionNamePassword("admin", "super")).thenReturn(false);
		
		String result = connectionController.getShoppingOnLineHomeAdmin(anyString(), anyString(), request);
		
		assertNotNull(result);
		assertNotSame(connectionController.SHOPPING_ONLINE_HOME_ADMIN, result);
		assertSame(MESSAGE_USER_NOT_CONNECTED, connectionController.REDIRECT, result);
	}
	
}
