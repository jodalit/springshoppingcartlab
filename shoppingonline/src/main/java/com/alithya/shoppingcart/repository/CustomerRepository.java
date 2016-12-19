package com.alithya.shoppingcart.repository;

import org.springframework.context.annotation.Scope;

import com.alithya.shoppingcart.model.Customer;


public interface CustomerRepository {
	public Customer getCustomer();
	public boolean updateAmount(Double amount, Long customerId);
}
