package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineSomeUtilitiesController;
import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ShoppingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineSomeUtilitiesControllerTest {
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineSomeUtilitiesController someUtilities;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		someUtilities = new ShoppingOnlineSomeUtilitiesController();
	}

	@Test
	public void verifyGetConnection() {
		
		ModelMap model = new ModelMap();
		String result =  someUtilities.getConnection(model, request);
		
		assertNotNull(model.containsKey(someUtilities.MODEL_NAME_CONNECTION));
		assertEquals(someUtilities.CONNEXION, model.get(someUtilities.MODEL_NAME_CONNECTION));
		assertSame(someUtilities.SHOPPING_ONLINE_CONNEXION, result);
		
	}
	
	@Test
	public void verifySumitConnection() {
		
		ModelMap model = new ModelMap();
		String result =  someUtilities.sumitConnection(model);
		
		assertNotNull(model.containsKey(someUtilities.MODEL_NAME_BASKET_SIZE));
		assertNotNull(model.containsKey(someUtilities.MODEL_NAME_BASKET));
		assertSame(someUtilities.SHOPPING_ONLINE_HOME_ADMIN, result);
	}
	
	@Test
	public void verifyGetShoppingOnlineHomeAdmin() {
		
		ModelMap model = new ModelMap();
		String result =  someUtilities.getShoppingOnlineHomeAdmin(model, request);
		
		assertNotNull(model.containsKey(someUtilities.MODEL_NAME_CONNECTION));
		assertEquals(someUtilities.CONNEXION, model.get(someUtilities.MODEL_NAME_CONNECTION));
	
		assertSame(someUtilities.SHOPPING_ONLINE_HOME_ADMIN, result);
	}
	
	@Test
	public void verifyGetNewUser() {
		
		ModelMap model = new ModelMap();
		String result =  someUtilities.getNewUser(model);
		
		assertNotNull(model.containsKey(someUtilities.MODEL_NAME_NEWUSER));
		assertSame(someUtilities.SHOPPING_ONLINE_NEWUSER, result);
	}
	
}
