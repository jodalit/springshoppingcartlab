package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineBasketController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.shoppingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
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
		Long id = Long.valueOf(12);
		Map<Long, Item> basket = new HashMap<>();
		when(basketServiceMock.getItemsBasket()).thenReturn(basket );
		
		
		String result =  basketController.addToBasketFromResultSearch(id, request);
		
		assertNotNull(request.getSession().getAttribute("basket"));
		assertSame( basketController.SHOPPING_ONLINE_SEARCH_RESULT, result);
	}

	@Test
	public void testAddToBasketFromResultSearch() {
		
	}

	@Test
	public void testPayItems() {
		Map<Long, Item> basket = new HashMap<>();
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		when(basketServiceMock.removeItemsToBasket()).thenReturn(true);
		
		String result = basketController.payItems(request);
		
		assertNull(request.getSession().getAttribute("basket"));
		assertSame(0,request.getSession().getAttribute("basketsize"));
		assertSame(0,request.getSession().getAttribute("baskettotal"));
		assertSame(basketController.SHOPPING_ONLINE_BASKET_RECEIVED, result);
	}

	@Test
	public void testRemoveItemFromBasket() {
		Map<Long, Item> basket = new HashMap<>();
		Item item = new Item(Long.valueOf(14),"allo", "allo", 12.2D, LocalDate.now());
		basket.put(Long.valueOf(14),item);
		//basketServiceMock.setBasketData(basketData);(basket);
		int basketSize = basket.size();
		request.getSession().setAttribute("basketsize", basketSize);
		
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		
		String result = basketController.removeItemFromBasket(14L, request);
		
		assertSame(0, request.getSession().getAttribute("basketsize"));
		assertSame(basketController.SHOPPING_ONLINE_BASKET, result);
	}

}
