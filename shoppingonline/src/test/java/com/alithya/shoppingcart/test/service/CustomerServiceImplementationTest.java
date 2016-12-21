package com.alithya.shoppingcart.test.service;

import static org.junit.Assert.*;

import org.junit.After;
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
import com.alithya.shoppingcart.repository.PaiementRepository;
import com.alithya.shoppingcart.repository.ItemRepository;
import com.alithya.shoppingcart.service.PaiementService;
import com.alithya.shoppingcart.service.PaiementServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class CustomerServiceImplementationTest {
	@Mock
	PaiementRepository customerRepositoryMock;
	
	private PaiementService paiementService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		paiementService = new PaiementServiceImplementation();
		paiementService.setCustomerRepository(customerRepositoryMock);
	}

	@Test
	public void verifyGetAvailableAmount() {
		
	}

}
