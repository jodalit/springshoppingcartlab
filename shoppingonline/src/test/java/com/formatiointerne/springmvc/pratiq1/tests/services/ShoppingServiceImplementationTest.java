package com.formatiointerne.springmvc.pratiq1.tests.services;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ShoppingServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
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
	public void verifyAllServices_areNotNull(){
		assertNotNull(itemServiceMock);
		assertNotNull(shoppingService);
	}
	
	@Test
	public void verifyItemMap_isNotNull() {
		assertNotNull(itemServiceMock.getItems());
	}
		
	@Test
	public void verifyExistItemInBasketOrNo_isNotNull() {
		assertNotNull(shoppingService.existItemInBasketOrNo(anyLong()));
	}
	
	@Test
	public void verifyExistItemInBasketOrNo_isNotEqual() {
		
		assertNotEquals(true, shoppingService.existItemInBasketOrNo(anyLong()));
	}
	
	@Test
	public void verifyGetAllItems_isNotNull() {
		List<Item> list = new ArrayList<>();
		
		//assertEquals(itemServiceMock.getItems().get(anyLong()).getItemName().isEmpty());
		when(itemServiceMock.getItems()).thenReturn(Matchers.anyMap());
		//assertNotNull(shoppingService.getAllItems());
	}
	
	@Test
	public void testAddItemToBasket() {
		Set<Item> items = new HashSet<>();
		Item item = new Item(17L, "item 145", "Item orekj398 irewItem 1", 8.45D);
				
		when(itemServiceMock.getItemById(2L)).thenReturn(anyObject());
		
		shoppingService.addItemToBasket(2L);
		assertNotNull(shoppingService.getBasket());
		
	}

}
