package com.formatiointerne.springmvc.pratiq1.tests.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

public class ShoppingServiceImplementationTest {
	@Mock
	ItemServiceImplementation itemServiceMock;
	
	ShoppingServiceImplementation shoppingService; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		shoppingService = new ShoppingServiceImplementation();
	}

	@After
	public void tearDown() throws Exception {
		shoppingService = null;
	}

	@Test
	public void testAddItemToBasket() {
		Set<Item> items = new HashSet<>();
		Item item = new Item(17L, "item 145", "Item orekj398 irewItem 1", 8.45D);
		items.add(item);
		
		when(itemServiceMock.getItemById(17L)).thenReturn(item);
		shoppingService.addItemToBasket(17L);
		
	}

}
