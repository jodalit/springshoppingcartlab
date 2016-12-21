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
import com.alithya.shoppingcart.controller.ShoppingOnLinePaiementRestController;
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.service.PaiementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnLinePaiementRestControllerTest {
	
	@Mock
	private PaiementService paiementServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnLinePaiementRestController paiementRestController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		paiementRestController = new ShoppingOnLinePaiementRestController();
		paiementRestController.setPaiementService(paiementServiceMock);
	}

	@Test
	public void verifyPayItemsWithEmptyBasket() {
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
		
		when(paiementServiceMock.purchaseItem(basketCustomer, customer)).thenReturn(true);
		when(paiementServiceMock.getErrors()).thenReturn(new HashMap<>());
				
		String result = paiementRestController.payItems(request);
		
		assertFalse(String.join(" ", "<h2 style='color:green;'>Your ticket ($) : ", basketCustomer.getBasketTotalAmount().toString(),"</h2>", "<h3 style='color:bleue;'>For these Items : </h3>", items.toString()).equals(result));
	}
	
	@Test
	public void verifyRechargeAccount() {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		request.getSession().setAttribute("customer", customer);;
		
		Double amount = 27.109D;
		
		when(paiementServiceMock.recharge(amount, 1L)).thenReturn(true);
		when(paiementServiceMock.getErrors()).thenReturn(new HashMap<>());
		
		String result = paiementRestController.rechargeAccount(amount, request);
		
		assertEquals(String.join(" ", "<h2 style='color:green;'>INFO : ", "</h2>", "<h3 style='color:bleue;'>Your recharge is performed with success.</h3>"), result);
		
	}
	
}
