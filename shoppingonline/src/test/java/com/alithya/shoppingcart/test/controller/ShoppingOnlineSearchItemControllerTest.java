package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.internal.compiler.apt.model.IElementInfo;
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
import com.alithya.shoppingcart.model.Item;
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

	@Test
	public void verifyGetResultSearchItem_With_HTTP_POST_METHOD() {
		
		String itemToFind =  "Training";
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Set<Item> items = new HashSet<>();
		items.add(item);
		
		when(itemServiceMock.getItemByNameDescription(itemToFind)).thenReturn(items);
		
		String result = searchItem.getResultSearchItem(itemToFind, request);
		
		assertNotNull(result);
		assertEquals(1, request.getSession().getAttribute(searchItem.SESSION_SIZE_ITEMS_FOR_NAME));
		assertSame(searchItem.SHOPPING_ONLINE_SEARCH_RESULT, result);
		
	}
	
	@Test
	public void verifyGetSearchItem_With_HTTP_GET_METHOD() {
		
		String result = searchItem.getSearchItem(request);
		assertNotNull(result);
		
		assertEquals(searchItem.RESULTSEARCHTITLE, request.getSession().getAttribute(searchItem.SESSION_RESULT_SEARCH));
		assertSame(searchItem.SHOPPING_ONLINE_SEARCH_RESULT, result);
	}
}
