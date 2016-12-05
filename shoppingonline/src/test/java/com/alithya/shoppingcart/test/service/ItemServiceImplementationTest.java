package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ItemServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ItemServiceImplementationTest {
	@Autowired
	private ItemService itemService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		itemService = new ItemServiceImplementation();
	}
	
	@Test
	public void verifySetOfPeopleNotNull() {
		assertNotNull(itemService.getItems());
	}
	
	@Test
	public void verifyCreateItemReturnPersonNotNull() {
		Item i = itemService.createItem("1", "AS", "AS", "12.5", LocalDate.now().toString());
		assertNotNull(i);
	}
	
	@Test
	public void verifyModifyNameDescriptionPriceExpiredateItemTrueAndNotNull(){
		assertNotNull(itemService.modifyNameDescriptionPriceExpiredateItem(3L, "Alithya", "Alithya", "12.0", LocalDate.now().toString()));
		assertTrue(itemService.modifyNameDescriptionPriceExpiredateItem(3L, "Alithya", "Alithya", "12.0", LocalDate.now().toString()));
		
	}
	
	@Test
	public void verifyGetItemByNameDescriptionAnyStringNotNull(){
		assertNotNull(itemService.getItemByNameDescription(anyString()));
	}
	
	@Test
	public void verifyRemoveItemAnyLongTrue(){
		assertTrue(itemService.removeItem(anyLong()));
	}
	
	@Test
	public void verifyRemoveItemNotValidLongNotSame(){
		assertNotSame(true, itemService.removeItem(Long.valueOf("the")));
	}
	
	@Test
	public void verifyGetMaxItemIdNotNull(){
		assertNotNull(itemService.getMaxItemId());
	}
		
	@Test
	public void verifyIsNumericNotNull(){
		assertNotNull(itemService.getMaxItemId());
	}
	
}
