package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.junit.Assert.*;

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

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineConnexion;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineSearchItemTest {
	@Mock
	private ItemServiceImplementation itemServiceMock;
	@Mock
	private ShoppingServiceImplementation shoppingServiceMock;
	@Autowired
	MockHttpServletRequest request;
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request.setParameter("itemNameToFind", "item 1");		
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void verifyAllMockServices_areNotNull(){
		assertNotNull(itemServiceMock);
		assertNotNull(shoppingServiceMock);
	}

	@Test
	public void testGetResultSearchItem_for_HttpPost() {
		
	}

}
