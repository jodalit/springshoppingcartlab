package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alithya.shoppingcart.configuration.ShoppingOnlineAPISecurityConfiguration;
import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineGlobalMethodSecurityConfiguration;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnLineBasketController;
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.BasketService;
import com.alithya.shoppingcart.service.FinancialService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class, ShoppingOnlineAPISecurityConfiguration.class, ShoppingOnlineGlobalMethodSecurityConfiguration.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineBasketControllerTest {
	
	@Mock
	private BasketService basketServiceMock;
	@Mock
	private FinancialService customerServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnLineBasketController basketController;
	
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		basketController = new ShoppingOnLineBasketController();
		basketController.setBasketService(basketServiceMock);
		basketController.setFinancialService(customerServiceMock);
	}
	
	@After
	public void tearDow() {
		SecurityContextHolder.clearContext();
	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void testGetBasketWithoutAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(customerServiceMock.getCustomerInfo()).thenReturn(customer);
		
		String result =  basketController.getBasket(request);
		assertFalse(throwAccessDeniedException(("You don't have authority")));
	}
	
	@Test
	public void testGetBasketWithAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1", "ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);																																																																						
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(customerServiceMock.getCustomerInfo()).thenReturn(customer);
		
		String result =  basketController.getBasket(request);
		
		assertSame(basketController.SHOPPING_ONLINE_BASKET, result);
	}
	
	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void testAddToBasketWithoutAuthentication() {
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.addItemToBasket(id)).thenReturn(new Basket());
		
		request.getSession().setAttribute("basket", basket);
		String result =  basketController.addToBasket(id, request);
		
		assertFalse(throwAuthenticationCredentialsNotFoundException("You don't authenticate"));
	}
	
	@Test
	public void testAddToBasketWithAppropriateAuthentication() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.addItemToBasket(id)).thenReturn(new Basket());
		
		request.getSession().setAttribute("basket", basket);
		String result =  basketController.addToBasket(id, request);
		
		assertSame( basketController.SHOPPING_ONLINE_HOME, result);
	}

	public void testAddToBasketFromResultSearch() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1", "ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Long id = 4L;
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		
		Map<Long, Item> basket = new HashMap<>();
		basket.put(id, item);
		when(basketServiceMock.addItemToBasket(id)).thenReturn(new Basket());
		when(basketServiceMock.getItemsBasket()).thenReturn(basket);
		
		request.getSession().setAttribute("basket", basket);
		String result =  basketController.addToBasketFromResultSearch(id, request);
		
		assertSame( basketController.SHOPPING_ONLINE_SEARCH_RESULT, result);
	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void testRemoveItemFromWithoutAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
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
		
		assertFalse(throwAccessDeniedException(("You don't have authority")));
	}
	
	@Test
	public void testRemoveItemFromWithAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1", "ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
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
		
		assertSame(basketController.SHOPPING_ONLINE_BASKET, result);
	}
	
	private boolean throwAccessDeniedException(String result) {
        throw new org.springframework.security.access.AccessDeniedException(result);
    }
	
	private boolean throwAuthenticationCredentialsNotFoundException(String result) {
        throw new AuthenticationCredentialsNotFoundException(result);
    }

}
