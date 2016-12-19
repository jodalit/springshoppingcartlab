package com.alithya.shoppingcart.service;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;

public interface CustomerService {
	public Double getAvailableAmount();
	public boolean recharge(Double amount);
	public boolean purchaseItem(Basket customerBasket, Customer customer);
}
