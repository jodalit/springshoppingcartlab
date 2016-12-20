package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineBasketControllerTest {
	
	@Mock
	private BasketService basketServiceMock;
	@Mock
	private CustomerService customerServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineBasketController basketController;
	
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		basketController = new ShoppingOnlineBasketController();
		basketController.setBasketService(basketServiceMock);
		basketController.setCustomerService(customerServiceMock);
	}

	@Test
	public void testGetBasket() {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(customerServiceMock.getCustomerInfo()).thenReturn(customer);
		
		String result =  basketController.getBasket(request);
		
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
	public void testRemoveItemFromEmptyBasket() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> basket = new HashMap<>();
		basket.put(4L, item);
		
		Basket basketData = new Basket();
		basketData.setBasketItems(basket);
		basketData.setBasketQuantity(1);
		basketData.setBasketTotalAmount(31.31D);
		
		basketServiceMock.setBasketData(basketData);
		when(basketServiceMock.removeItemToBasket(4L)).thenReturn(true);
		String result = basketController.removeItemFromBasket(4L, request);
		
		assertSame(basketController.REDIRECT, result);
	}
	
	

}
