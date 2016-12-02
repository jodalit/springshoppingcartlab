package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineHome;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineHomeTest {
	@Autowired
	private ItemService itemService;
		
	@Autowired 
	MockHttpServletRequest request;
	
	@Mock
	private ItemService itemServiceMock;
	private ShoppingOnlineHome home;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		home = new ShoppingOnlineHome();
		home.setItemService(itemServiceMock);
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("End test");
	}

	@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(itemService);
	}
	
	@Test
	public void verifyAllMockServices_areNotNull(){
		assertNotNull(itemServiceMock);
	}
	
	@Test
	public void verifyGoShoppingOnlineHome_returnNamePage() {
		Map<Long, Item> items = new HashMap<>();
		when(itemServiceMock.getItems()).thenReturn(items);
		ModelMap model = new ModelMap();
		String pagename = home.goShoppingOnlineHome(model, request);
		assertNotNull(pagename);
		assertEquals("shoppingonlinehome", pagename);
	}
	
	
}
