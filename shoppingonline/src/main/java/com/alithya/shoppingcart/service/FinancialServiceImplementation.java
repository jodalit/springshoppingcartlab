package com.alithya.shoppingcart.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.exception.BusinessException;
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.FinancialRepository;

@Service
public class FinancialServiceImplementation implements FinancialService {
	@Autowired
	private FinancialRepository financialRepository;
	
	@Autowired
	private BasketService basketService;
	
	private Map<String, String> errors = new HashMap<String, String>();
		
	@Override
	public Double getAvailableAmount() {
		Customer customer = this.getCustomerInfo();
		try {
			return customer.getCustomerAvailableAmount();
		} catch (BusinessException e) {
			setError("customerAvailableAmount", e.getMessage());
			return 0D;
		}
		
	}

	@Override
	public boolean recharge(Double amount, Long id) {	
		Double existingAmount = this.getCustomerInfo().getCustomerAvailableAmount();
		Double newAmount = existingAmount + amount;
		
		try {
			
			if (!financialRepository.updateAmount(newAmount, id)){
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			
			setError("customerAvailableAmount", e.getMessage());
			return false;
		}
			
	}
	
	@Override
	public boolean purchaseItem(String customerBasketReference) {
		
		Basket customerBasket = basketService.getBasketData();
		
		String varCustomerBasketRef = customerBasket.getBasketReference();
		try {
			basketValidation(customerBasketReference, varCustomerBasketRef);
		} catch (BusinessException e) {
			setError("emptybasket", e.getMessage());	
			return false;
		}
		
		Customer currentCustomer = customerBasket.getCustomer();
		Double currentCustomerAvailableAmount = currentCustomer.getCustomerAvailableAmount();
		Double varCustomerBasketTotalAmount = basketService.getBasketData().getBasketTotalAmount();
		
		try {
			accountBalanceValidation(currentCustomerAvailableAmount, varCustomerBasketTotalAmount);
		} catch (BusinessException e) {
			setError("accountbalance", e.getMessage());
			return false;
		}
		
		currentCustomerAvailableAmount = currentCustomerAvailableAmount-varCustomerBasketTotalAmount;
		try {
			if (!financialRepository.updateAmount(currentCustomerAvailableAmount, currentCustomer.getCustomerId())){
				setError("customerAvailableAmount", "You do not have much money!");
				return false;
			}
		} catch (SQLException e) {
			setError("connection", String.join(" ", "Database unavailable!", e.getMessage()));
			return false;
		}
		
		return true;
	}
	
	@Override
	public void basketValidation(String customerBasketRef, String varCustomerBasketRef){
		
		if (customerBasketRef==null){
			throw new BusinessException("Your basket is empty!");
		} else{
			if (!varCustomerBasketRef.equalsIgnoreCase(customerBasketRef))
				throw new BusinessException("Your basket does not exist!");
		}
	}
	
	@Override
	public void accountBalanceValidation(Double currentCustAvailableAmount, Double varCustBasketTotalAmount) {
		if (varCustBasketTotalAmount > currentCustAvailableAmount){
			throw new BusinessException("You do not have much money!");
		}
	}
	
	@Override
	public Customer getCustomerInfo() {
		try {
			
			return financialRepository.getCustomer();
			
		} catch (SQLException e) {
			
			setError("connection", String.join(" ", "Database unavailable!", e.getMessage()));
			return null;
			
		}
	}
	
	@Override
	public void setFinancialRepository(FinancialRepository financialRepository) {
		this.financialRepository = financialRepository;
	}
	
	@Override
	public void setError( String target, String message ) {
		errors.put( target, message );
	}
	
	@Override
	public Map<String, String> getErrors() {
		return errors;
	}
	
}
