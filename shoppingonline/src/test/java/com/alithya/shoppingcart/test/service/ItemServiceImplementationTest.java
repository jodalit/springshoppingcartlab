package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.ItemServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ItemServiceImplementationTest {
	@Mock
	ItemRepository itemRepositoryMock;
	
	@Autowired
	private ItemService itemService;
	private ApplicationContext ctx;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		itemService = new ItemServiceImplementation();
		itemService.setItemRepository(itemRepositoryMock);
	}
	
	@Test
	public void verifySetOfPeopleNotNull() {
		assertNotNull(itemService.getItemsList());
	}
	
	@Test
	public void verifyCreateItem() {
		
		when(itemRepositoryMock.insertItem("AS", "AS", "12.5", LocalDate.now().toString())).thenReturn(true);
		
		Long i = itemService.createItem("ALLO", "ALITHYA", "256.5", LocalDate.now().toString());
		assertTrue(i.longValue()==0);
		assertEquals(0, i.longValue());
	}
	
	@Test
	public void verifyModifyNameDescriptionPriceExpiredateItem(){
	
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Item item1 = new Item(Long.valueOf(19),"CDE", "Alithya's CDE", 27.27D, LocalDate.now());
		
		Map<Long, Item> mapItems = new HashMap<>();
		mapItems.put(18L, item);
		mapItems.put(19L, item1);
		//itemService.setItems(mapItems);
		
		List<Item> items = new ArrayList<>();
		items.add( item);
		items.add( item1);
		
		when(itemRepositoryMock.getAllItems()).thenReturn(items);
		when(itemRepositoryMock.updateItem(18L, "CDE", "CDE", "45.7D", LocalDate.now().toString())).thenReturn(true);
		
		Map<Long, Item> m = itemService.getItemsList();
		
		boolean response  = itemService.modifyNameDescriptionPriceExpiredateItem(18L, "CDE", "CDE", "45.7D", LocalDate.now().toString());
		
		assertTrue(response);
		
	}
	
	@Test
	public void verifyGetItemByNameDescriptionAnyStringNotNull(){
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Item item1 = new Item(Long.valueOf(19),"CDE", "Alithya's CDE", 27.27D, LocalDate.now());
		
		Map<Long, Item> mapItems = new HashMap<>();
		mapItems.put(18L, item);
		mapItems.put(19L, item1);
		//itemService.setItems(mapItems);
		
		List<Item> items = new ArrayList<>();
		items.add( item);
		items.add( item1);
		
		when(itemRepositoryMock.getAllItems()).thenReturn(items);
		
		Map<Long, Item> m = itemService.getItemsList();
		
		Set<Item> result = itemService.getItemByNameDescription("Center");
		
		assertTrue(result.size()==1);
		assertEquals ((long)1, (long)result.size());
	}
	
	@Test
	public void verifyRemoveItemAnyLongTrue(){
		
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Item item1 = new Item(Long.valueOf(19),"CDE", "Alithya's CDE", 27.27D, LocalDate.now());
		
		Map<Long, Item> mapItems = new HashMap<>();
		mapItems.put(18L, item);
		mapItems.put(19L, item1);
		//itemService.setItems(mapItems);
		
		List<Item> items = new ArrayList<>();
		items.add( item);
		items.add( item1);
		
		when(itemRepositoryMock.getAllItems()).thenReturn(items);
		when(itemRepositoryMock.deleteItem(18L)).thenReturn(true);
		
		Map<Long, Item> m = itemService.getItemsList();
		
		boolean response = itemService.removeItem(18L);
		
		assertTrue(response);
	}
	
	
	@Test
	public void verifyGetMaxItemIdNotNull(){
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Item item1 = new Item(Long.valueOf(19),"CDE", "Alithya's CDE", 27.27D, LocalDate.now());
		
		Map<Long, Item> mapItems = new HashMap<>();
		mapItems.put(18L, item);
		mapItems.put(19L, item1);
		//itemService.setItems(mapItems);
		
		List<Item> items = new ArrayList<>();
		items.add( item);
		items.add( item1);
		
		when(itemRepositoryMock.getAllItems()).thenReturn(items);
		
		Map<Long, Item> m = itemService.getItemsList();
		
		Long response = itemService.getMaxItemId();
		assertFalse(response.longValue()==0);
		assertEquals(19, response.longValue());
	}
		

}
