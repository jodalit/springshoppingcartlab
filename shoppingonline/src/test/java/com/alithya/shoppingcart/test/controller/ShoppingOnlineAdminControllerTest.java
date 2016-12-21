package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineConnexionController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineAdminControllerTest {
	
	private ShoppingOnlineConnexionController adminController;
	
	@Autowired
	MockHttpServletRequest request;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminController = new ShoppingOnlineConnexionController();
	}

	@Test
	public void verifyGetNewClient() {
		String pagename = adminController.getShoppingOnLineHomeAdmin("admin", "admin", request);
		
		assertEquals(adminController.SHOPPING_ONLINE_HOME_ADMIN, pagename);
	}

}
