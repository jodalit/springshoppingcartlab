package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;

import com.alithya.shoppingcart.controller.ShoppingOnlineAdminController;
import com.alithya.shoppingcart.model.Item;

public class ShoppingOnlineAdminControllerTest {
	private ShoppingOnlineAdminController adminController;
	@Autowired
	MockHttpServletRequest request;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminController = new ShoppingOnlineAdminController();
	}

	@Test
	public void verifyGetNewClient() {
		ModelMap model = new ModelMap();
		String pagename = adminController.getNewClient(model);
		assertNotNull(pagename);
		assertEquals(adminController.SHOPPING_ONLINE_HOME_ADMIN, pagename);
	}

}
