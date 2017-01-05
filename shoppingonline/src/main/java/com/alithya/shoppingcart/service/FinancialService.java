package com.alithya.shoppingcart.service;

import java.util.Map;

import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.FinancialRepository;

public interface FinancialService {
	public Customer getCustomerInfo();
	public Double getAvailableAmount();
	public boolean recharge(Double amount, Long id);
	//public boolean purchaseItem(Basket customerBasket, Customer customer);
	public boolean purchaseItem(String customerBasketReference);
	public void basketValidation(String custBasketRef, String varCustBasketRef);
	public void accountBalanceValidation(Double currentCustAvailAmount, Double varCustBasketTotAmount);
	public Map<String, String> getErrors();
	public void setError( String target, String message );
	void setFinancialRepository(FinancialRepository financialRepository);
}
