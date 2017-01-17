package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

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

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnLineFinancialRestController;
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.FinancialService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnLineFinancialRestControllerTest {
	
	@Mock
	private FinancialService financialServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnLineFinancialRestController financialRestController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		financialRestController = new ShoppingOnLineFinancialRestController();
		financialRestController.setFinancialService(financialServiceMock);
	}
	
	@After
	public void tearDow() {
		SecurityContextHolder.clearContext();
	}

	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void verifyPayItemsWithoutAuthentication() {
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		Basket basketCustomer = new Basket();
		basketCustomer.setBasketItems(items);
		basketCustomer.setBasketQuantity(1);
		basketCustomer.setBasketTotalAmount(31.31D);
		
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		request.getSession().setAttribute("basketdata", basketCustomer);
		request.getSession().setAttribute("customer", customer);
		
		when(financialServiceMock.purchaseItem(anyString())).thenReturn(true);
		when(financialServiceMock.getErrors()).thenReturn(new HashMap<>());
				
		String result = financialRestController.payItems(anyString(), request);
		
		assertFalse(String.join(" ", "<h2 style='color:green;'>Your ticket ($) : ", basketCustomer.getBasketTotalAmount().toString(),"</h2>", "<h3 style='color:bleue;'>For these Items : </h3>", items.toString()).equals(result));
		assertFalse(throwAuthenticationCredentialsNotFoundException("You don't authenticate"));
	}
	
	@Test
	public void verifyPayItemsWithAuthentication() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", 31.31D);
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		Basket basketCustomer = new Basket();
		basketCustomer.setBasketItems(items);
		basketCustomer.setBasketQuantity(1);
		basketCustomer.setBasketTotalAmount(31.31D);
		
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		request.getSession().setAttribute("basketdata", basketCustomer);
		request.getSession().setAttribute("customer", customer);
		
		when(financialServiceMock.purchaseItem(anyString())).thenReturn(true);
		when(financialServiceMock.getErrors()).thenReturn(new HashMap<>());
				
		String result = financialRestController.payItems(anyString(), request);
		
		assertFalse(String.join(" ", "<h2 style='color:green;'>Your ticket ($) : ", basketCustomer.getBasketTotalAmount().toString(),"</h2>", "<h3 style='color:bleue;'>For these Items : </h3>", items.toString()).equals(result));
	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void verifyRechargeAccountWithoutAppropriateAuthority() {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		request.getSession().setAttribute("customer", customer);;
		
		Double amount = 27.109D;
		
		when(financialServiceMock.recharge(amount, 1L)).thenReturn(true);
		when(financialServiceMock.getErrors()).thenReturn(new HashMap<>());
		
		String result = financialRestController.rechargeAccount(amount, request);
		
		assertEquals(String.join(" ", "<h2 style='color:green;'>INFO : ", "</h2>", "<h3 style='color:bleue;'>Your recharge is performed with success.</h3>"), result);
		assertFalse(throwAccessDeniedException(("You don't have authority")));
	}
	
	@Test
	public void verifyRechargeAccountWithAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1", "ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		request.getSession().setAttribute("customer", customer);;
		
		Double amount = 27.109D;
		
		when(financialServiceMock.recharge(amount, 1L)).thenReturn(true);
		when(financialServiceMock.getErrors()).thenReturn(new HashMap<>());
		
		String result = financialRestController.rechargeAccount(amount, request);
		
		assertEquals(String.join(" ", "<h2 style='color:green;'>INFO : ", "</h2>", "<h3 style='color:bleue;'>Your recharge is performed with success.</h3>"), result);
	}
	
	private boolean throwAccessDeniedException(String result) {
        throw new org.springframework.security.access.AccessDeniedException(result);
    }
	
	private boolean throwAuthenticationCredentialsNotFoundException(String result) {
        throw new AuthenticationCredentialsNotFoundException(result);
    }
	
}
