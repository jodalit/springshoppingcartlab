package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.FinancialRepository;
import com.alithya.shoppingcart.service.FinancialService;
import com.alithya.shoppingcart.service.FinancialServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class FinancialServiceImplementationTest {
	@Mock
	FinancialRepository financialRepositoryMock;
	
	private FinancialService financialService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		financialService = new FinancialServiceImplementation();
		financialService.setFinancialRepository(financialRepositoryMock);
	}

	@Test
	public void verifyGetAvailableAmount() throws SQLException {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(financialRepositoryMock.getCustomer()).thenReturn(customer);
		
		Double result = financialService.getAvailableAmount();
		
		assertTrue(result==1500D);
	}
	
	@Test
	public void verifyGetCustomerInfo() throws SQLException {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(financialRepositoryMock.getCustomer()).thenReturn(customer);
		 
		Customer result = financialService.getCustomerInfo();
		
		assertEquals(customer, result);
	}
		
	@Test
	public void verifyPurchaseItem() throws SQLException {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(Double.valueOf(1500));
		customer.setPerson(p);
		
		Item item = new Item(4L, "Item 12 Montréal  orekj398", " irewItem 1", Double.valueOf(50));
		Map<Long, Item> items = new HashMap<>();
		items.put(4L, item);
		
		Basket basketData = new Basket();
		basketData.setBasketItems(items);
		basketData.setBasketQuantity(1);
		basketData.setBasketTotalAmount(Double.valueOf(50));
		
		when(financialRepositoryMock.updateAmount(Double.valueOf(1450), 2L)).thenReturn(true);
		
		financialService.basketValidation(anyString(), anyString());;
		financialService.accountBalanceValidation(anyDouble(), anyDouble());
				
		boolean result = financialService.purchaseItem(anyString());
		
		assertTrue(result);
	}
	
	@Test
	public void verifyRecharge() throws SQLException {
		Person p = new Person(Long.valueOf(2), "customer1", null, LocalDate.now(), null, null, "customer1", "customer1", 2);
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerAvailableAmount(1500D);
		customer.setPerson(p);
		
		when(financialRepositoryMock.updateAmount(27D, 2L)).thenReturn(true);
		 
		boolean result = financialService.recharge(Double.valueOf(1527), 2L);
		
		assertEquals(Double.valueOf(1527), financialRepositoryMock.getCustomer().getCustomerAvailableAmount());
		assertTrue(result);
	}

}
