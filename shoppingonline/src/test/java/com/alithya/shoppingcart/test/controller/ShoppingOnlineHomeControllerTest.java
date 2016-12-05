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
public class ShoppingOnlineHomeControllerTest {
	@Autowired 
	MockHttpServletRequest request;
	
	@Mock
	private ItemService itemServiceMock;
	private ShoppingOnlineHomeController home;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		home = new ShoppingOnlineHomeController();
		home.setItemService(itemServiceMock);
	}
	
	@Test
	public void verifyGoShoppingOnlineHomeReturnNamePage() {
		Map<Long, Item> items = new HashMap<>();
		when(itemServiceMock.getItems()).thenReturn(items);
		ModelMap model = new ModelMap();
		String pagename = home.goShoppingOnlineHome(model, request);
		assertNotNull(pagename);
		assertEquals("shoppingonlinehome", pagename);
	}
		
}
