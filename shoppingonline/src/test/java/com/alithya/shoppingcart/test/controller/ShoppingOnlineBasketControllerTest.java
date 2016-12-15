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

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineBasketController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.BasketService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineBasketControllerTest {
	
	@Mock
	private BasketService basketServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineBasketController basketController;
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		basketController = new ShoppingOnlineBasketController();
		basketController.setBasketService(basketServiceMock);
	}

	@Test
	public void testGetBasket() {
		String result =  basketController.getBasket();
		
		assertSame(basketController.SHOPPING_ONLINE_BASKET, result);
	}

	@Test
	public void testAddToBasket() {
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.addItemToBasket(id)).thenReturn(true);
		
		request.getSession().setAttribute("basket", basket);
		String result =  basketController.addToBasket(id, request);
		
		assertSame( basketController.SHOPPING_ONLINE_HOME, result);
	}

	@Test
	public void testAddToBasketFromResultSearch() {
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.addItemToBasket(id)).thenReturn(true);
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		
		request.getSession().setAttribute("basket", basket);
		String result =  basketController.addToBasketFromResultSearch(id, request);
		
		assertSame( basketController.SHOPPING_ONLINE_SEARCH_RESULT, result);
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

	@Test
	public void testRemoveItemFromEmptyBasket() {
		Map<Long, Item> basket = new HashMap<>();
			
		String result = basketController.removeItemFromBasket(14L, request);
		
		assertSame(null, request.getSession().getAttribute(basketController.SESSION_BASKETSIZE));
		assertSame(basketController.REDIRECT, result);
	}
	
	

}
