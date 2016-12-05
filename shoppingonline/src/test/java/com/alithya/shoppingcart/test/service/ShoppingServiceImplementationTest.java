package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemServiceImplementation;
import com.alithya.shoppingcart.service.ShoppingServiceImplementation;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ShoppingServiceImplementationTest {
	@Mock
	ItemServiceImplementation itemServiceMock;
	
	ShoppingServiceImplementation shoppingService; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shoppingService = new ShoppingServiceImplementation();	
		shoppingService.setItemService(itemServiceMock);
	}
	
	@Test
	public void verifyItemMap() {
		assertNotNull(itemServiceMock.getItems());
	}
	
	@Test
	public void verifyExistItemInBasketWhenBasketIsEmpty() {
		assertNotEquals(true, shoppingService.existItemInBasketOrNo(2L));
	}
	
	@Test
	public void verifyExistItemInBasket() {
		Set<Item> basket = new HashSet<>();
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		basket.add(item);
		assertNotNull(shoppingService.getBasket());
		shoppingService.setBasket(basket);
		assertEquals(true, shoppingService.existItemInBasketOrNo(4L));
	}
	
	@Test
	public void verifyGetAllItems() {
		Map<Long, Item> items = new HashMap<>();
		when(itemServiceMock.getItems()).thenReturn(items);
		List<Item> l = shoppingService.getAllItems();
		assertNotNull(l);
	}
	

	@Test
	public void verifyAddItemToBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		itemServiceMock.setItems(items);
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		shoppingService.addItemToBasket(4L);
		assertNotNull(shoppingService.getBasket());
		assertEquals(1, shoppingService.getBasket().size());
	}
	
	@Test
	public void verifyRemoveAllItemsToBasket() {
		Set<Item> basket = new HashSet<>();
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		basket.add(item);
		shoppingService.setBasket(basket);
		assertNotNull(shoppingService.getBasket());
		
		shoppingService.removeItemsToBasket();
		assertEquals(0, shoppingService.getBasket().size());
	}
	
	@Test
	public void verifyRemoveItemToBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		itemServiceMock.setItems(items);
		when(itemServiceMock.getItemById(4L)).thenReturn(item);
		
		Set<Item> basket = new HashSet<>();
		basket.add(item);
		shoppingService.setBasket(basket);
		assertNotNull(shoppingService.getBasket());
		
		shoppingService.setPricetoberemoved(0.0);
		shoppingService.setTotalBasket(31.31D);
		shoppingService.removeItemToBasket(new Long(4));
		basket.remove(item);
		assertEquals(0, shoppingService.getBasket().size());
	}
	
	

}
