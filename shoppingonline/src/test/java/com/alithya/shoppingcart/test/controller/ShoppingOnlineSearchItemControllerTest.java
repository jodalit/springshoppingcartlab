package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.util.HashSet;

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
import com.alithya.shoppingcart.controller.ShoppingOnlineSearchItemController;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.shoppingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineSearchItemControllerTest {
	@Mock
	private ItemService itemServiceMock;
	@Mock
	private shoppingService shoppingServiceMock;
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineSearchItemController searchItem;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request.setParameter("itemNameToFind", "centre");	
		searchItem = new ShoppingOnlineSearchItemController();
		searchItem.setItemService(itemServiceMock);
		searchItem.setShoppingService(shoppingServiceMock);
	}

	//TODO you should have 2 tests, one for get and one for post
	@Test
	public void testGetResultSearchItemRetunValidPage() {
		String itemToFind =  (String)request.getAttribute("itemNameToFind");
		when(itemServiceMock.getItemByNameDescription(itemToFind)).thenReturn(new HashSet<>());
		String result = searchItem.getResultSearchItem(itemToFind, request);
		assertNotNull(result);
		assertSame("Display result page of search","shoppingonlinesearchresult", result);
		// TODO try to test the values of the following items as per how they
		// are filled in the controller:
	}
}
