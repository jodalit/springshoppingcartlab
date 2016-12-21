package com.alithya.shoppingcart.repository;

import java.sql.SQLException;

import org.springframework.context.annotation.Scope;

import com.alithya.shoppingcart.exception.BusinessException;
import com.alithya.shoppingcart.model.Customer;


public interface PaiementRepository {
	public Customer getCustomer() throws BusinessException;
	public boolean updateAmount(Double amount, Long customerId) throws BusinessException;
}
