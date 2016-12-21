package com.alithya.shoppingcart.test.controller;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

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
import com.alithya.shoppingcart.controller.ShoppingOnlineHomeController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineHomeControllerTest {
	private static final String SHOPPING_ONLINE_HOME = "shoppingonlinehome";

	@Autowired 
	MockHttpServletRequest request;
	
	@Mock
	private ItemService itemServiceMock;
	private ShoppingOnlineHomeController homeController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		homeController = new ShoppingOnlineHomeController();
		homeController.setItemService(itemServiceMock);
	}
	
	@Test
	public void verifyGetShoppingOnlineHome() {
		Map<Long, Item> items = new HashMap<>();
		when(itemServiceMock.getItemsList()).thenReturn(items);
		
		ModelMap model = new ModelMap();
		String pagename = homeController.getShoppingOnlineHome(model, request);
		
		assertNotNull(pagename);
		assertTrue(model.containsKey(homeController.MODEL_NAME_WELCOME_TITLE));
		assertTrue(model.containsKey(homeController.MODEL_NAME_WELCOME_INFO));
		assertSame(homeController.WELCOME_TITLE, model.get(homeController.MODEL_NAME_WELCOME_TITLE));
		assertSame(homeController.WELCOME_INFO, model.get(homeController.MODEL_NAME_WELCOME_INFO));
		assertEquals(SHOPPING_ONLINE_HOME, pagename);
	}
		
}
