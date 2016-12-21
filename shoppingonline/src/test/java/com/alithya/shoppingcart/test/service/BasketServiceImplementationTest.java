package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.BasketServiceImplementation;
import com.alithya.shoppingcart.service.ItemService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class BasketServiceImplementationTest {
	
	@Mock
	private ItemService itemServiceMock;
	
	private BasketService basketService; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		basketService = new BasketServiceImplementation();
		basketService.setItemService(itemServiceMock);
	}

	@Test
	public void verifyAddItemToBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		boolean response = basketService.addItemToBasket(4L);
		
		assertSame(true, response);
		assertEquals(items, basketService.getBasketData().getBasketItems());
	}
	
	@Test
	public void verifyRemoveItemToBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		boolean b = basketService.addItemToBasket(4L);
		boolean response = basketService.removeItemToBasket(4L);
		
		assertTrue( b);
		assertSame(true, response);
		assertNotSame(items,basketService.getBasketData().getBasketItems());
		assertSame(0,basketService.getBasketData().getBasketItems().size());
	}
	
	@Test
	public void verifyRemoveAllItemsToBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		boolean b = basketService.addItemToBasket(4L);
		boolean response = basketService.removeItemsToBasket();
		
		assertTrue( b);
		assertSame(true, response);
		assertNull(basketService.getBasketData().getBasketItems());
		assertSame(0, basketService.getBasketData().getBasketQuantity());
		assertEquals(Double.valueOf(0), Double.valueOf(basketService.getBasketData().getBasketTotalAmount()));
	}
	
	@Test
	public void verifyRemoveItemOnItemsListOfClient() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		boolean b = basketService.addItemToBasket(4L);
		
		List<Item> itemsList = new ArrayList<>(items.values());
		boolean response = basketService.removeItemOnItemsListOfClient(item, itemsList);
		
		assertTrue( b);
		assertSame(true, response);
		assertEquals(0,basketService.getBasketData().getBasketItems().size());
	}

}
