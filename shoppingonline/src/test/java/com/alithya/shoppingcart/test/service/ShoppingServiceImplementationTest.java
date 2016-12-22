package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;

import java.util.List;

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
import com.alithya.shoppingcart.repository.ItemRepository;
import com.alithya.shoppingcart.service.ShoppingService;
import com.alithya.shoppingcart.service.ShoppingServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingServiceImplementationTest {
	@Mock
	ItemRepository itemRepositoryMock;
	
	ShoppingService shoppingService; 
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shoppingService = new ShoppingServiceImplementation();
		shoppingService.setItemRepository(itemRepositoryMock);
	}
	
	
	@Test
	public void verifyGetAllItems() {
		List<Item> items = shoppingService.getAllItems();
		Item item= items.get(0);
		assertSame("test", item.getItemName());
	}	

}
