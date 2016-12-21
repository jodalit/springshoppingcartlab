package com.alithya.shoppingcart.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.exception.BusinessException;
import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.PaiementRepository;

@Service
public class PaiementServiceImplementation implements PaiementService {
	@Autowired
	private PaiementRepository paiementRepository;
	
	private Map<String, String> errors = new HashMap<String, String>();
		
	@Override
	public Double getAvailableAmount() {
		Customer customer = paiementRepository.getCustomer();
		try {
			return customer.getCustomerAvailableAmount();
		} catch (BusinessException e) {
			setError("customerAvailableAmount", e.getMessage());
			return 0D;
		}
		
	}

	@Override
	public boolean recharge(Double amount, Long id) {	
		try {
			Double existingAmount =paiementRepository.getCustomer().getCustomerAvailableAmount();
			Double newAmount = existingAmount + amount;
			
			paiementRepository.updateAmount(newAmount, id);
			
			return true;
		} catch (BusinessException e) {
			setError("customerAvailableAmount", e.getMessage());
			return false;
		}
			
	}

	@Override
	public boolean purchaseItem(Basket customerBasket, Customer customer) {
		try {
			basketValidation(customerBasket);
		} catch (BusinessException e) {
			setError("emptybasket", e.getMessage());
			return false;
		}
		
		Customer customerr = paiementRepository.getCustomer();
		
		try {
			accountBalanceValidation(customerBasket, customerr);
		} catch (BusinessException e) {
			setError("accountbalance", e.getMessage());
			return false;
		}
		
		Double availableAmount = customerr.getCustomerAvailableAmount();
		Double basketTotalAmount = customerBasket.getBasketTotalAmount();
		
		try {
			availableAmount = availableAmount-basketTotalAmount;
			if (customer.getCustomerId()!=customerr.getCustomerId())
				paiementRepository.updateAmount(availableAmount, customerr.getCustomerId());
			else
				paiementRepository.updateAmount(availableAmount, customer.getCustomerId());

			customerBasket.setBasketItems(null);
			customerBasket.setBasketQuantity(0);
			customerBasket.setBasketTotalAmount(0.0);
		
			return true;
		} catch (BusinessException e) {
			setError("customerAvailableAmount", e.getMessage());
			return false;
		}
	} 
	
	@Override
	public void basketValidation(Basket customerBasket) {
		if (customerBasket.getBasketItems()==null){
			throw new BusinessException("You do not have much money!");
		}
	}
	
	@Override
	public void accountBalanceValidation(Basket customerBasket, Customer customer) {
		
		Double availableAmount = customer.getCustomerAvailableAmount();
		Double basketTotalAmount = customerBasket.getBasketTotalAmount();
		
		if (basketTotalAmount > availableAmount){
			throw new BusinessException("Your basket is empty!");
		}
	}
	
	@Override
	public Customer getCustomerInfo() {
		return paiementRepository.getCustomer();
	}
	
	@Override
	public void setCustomerRepository(PaiementRepository paiementRepository) {
		this.paiementRepository = paiementRepository;
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
