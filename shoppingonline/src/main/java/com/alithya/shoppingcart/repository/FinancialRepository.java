package com.alithya.shoppingcart.repository;

import java.sql.SQLException;

import com.alithya.shoppingcart.model.Customer;


public interface FinancialRepository {
	public Customer getCustomer() throws SQLException;
	public boolean updateAmount(Double amount, Long customerId) throws SQLException;
}
