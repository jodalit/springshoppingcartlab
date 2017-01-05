package com.alithya.shoppingcart.test.repository.implementation;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alithya.shoppingcart.configuration.ShoppingOnlineRootApplicationContextConfig;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.FinancialRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineRootApplicationContextConfig.class})
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public class PaimentRepositotyImplementationTest {
	@Autowired
	FinancialRepository financialRepository;
	
	@Test
	public void verifyGetCustomer() throws SQLException {
		Customer customer = financialRepository.getCustomer();
		assertNotNull(customer);
	}
	
	@Test
	public void verifyUpdateAmount() throws SQLException {
		boolean response = financialRepository.updateAmount(Double.valueOf(125), Long.valueOf(2));
		assertTrue(response);
	}

}
