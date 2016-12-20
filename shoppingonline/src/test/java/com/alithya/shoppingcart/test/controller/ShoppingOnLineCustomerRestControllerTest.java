package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alithya.shoppingcart.model.Item;

public class ShoppingOnLineCustomerRestControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPayItemsWithEmptyBasket() {
		Map<Long, Item> basket = new HashMap<>();
		when(basketServiceMock.getItemsBasket()).thenReturn(null);
				
		String result = basketController.payItems(request);
		
		assertSame(basketController.REDIRECT, result);
	}
	
	@Test
	public void testPayItems() {
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		
		when(basketServiceMock.removeItemsToBasket()).thenReturn(true);
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		
		String result = basketController.payItems(request);
		
		assertNull(request.getSession().getAttribute(basketController.MODEL_NAME_BASKET));
		assertSame(null, request.getSession().getAttribute(basketController.SESSION_BASKETSIZE));
		assertSame(null,request.getSession().getAttribute(basketController.SESSION_BASKETTOTAL));
		assertSame(basketController.REDIRECT, result);
	}
	
	@Test
	public void testNotPayItems() {
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.removeItemsToBasket()).thenReturn(false);
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		
		String result = basketController.payItems(request);
		
		assertNull(request.getSession().getAttribute(basketController.MODEL_NAME_BASKET));
		assertSame(null, request.getSession().getAttribute(basketController.SESSION_BASKETSIZE));
		assertSame(null,request.getSession().getAttribute(basketController.SESSION_BASKETTOTAL));
		assertSame(basketController.SHOPPING_ONLINE_BASKET_RECEIVED, result);
	}

}
