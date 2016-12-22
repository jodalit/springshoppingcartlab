package com.alithya.shoppingcart.service;

import java.util.Map;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.PaiementRepository;

public interface PaiementService {
	public Customer getCustomerInfo();
	public Double getAvailableAmount();
	public boolean recharge(Double amount, Long id);
	public boolean purchaseItem(Basket customerBasket, Customer customer);
	public void basketValidation(Basket customerBasket);
	public void accountBalanceValidation(Basket customerBasket, Customer customer);
	public Map<String, String> getErrors();
	public void setError( String target, String message );
	void setPaiementRepository(PaiementRepository paiementRepository);
}
