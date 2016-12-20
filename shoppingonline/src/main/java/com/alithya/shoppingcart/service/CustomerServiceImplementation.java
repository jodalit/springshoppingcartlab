package com.alithya.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Double getAvailableAmount() {
		Customer customer = customerRepository.getCustomer();
		if (customer.getCustomerAvailableAmount()==null)
			return 0D;
		
		return customer.getCustomerAvailableAmount();
	}

	@Override
	public boolean recharge(Double amount, Long id) {
		if (amount==null)
			return false;
		
		if (customerRepository.getCustomer().getCustomerId()!=id)
			return false;
		
		Double existingAmount =customerRepository.getCustomer().getCustomerAvailableAmount();
		Double newAmount = existingAmount + amount;
		
		customerRepository.updateAmount(newAmount, id);
		
		return true;
	}

	@Override
	public boolean purchaseItem(Basket customerBasket, Customer customer) {
		if (customerBasket.getBasketItems()==null)
			return false;
		
		Double availableAmount = customerRepository.getCustomer().getCustomerAvailableAmount();
		Double basketTotalAmount = customerBasket.getBasketTotalAmount();
		
		if (basketTotalAmount > availableAmount)
			return false;
		
		availableAmount = availableAmount-basketTotalAmount;
		customerRepository.updateAmount(availableAmount, customer.getCustomerId());
		
		customerBasket.setBasketItems(null);
		customerBasket.setBasketQuantity(0);
		customerBasket.setBasketTotalAmount(0.0);
		
		return true;
	}

	@Override
	public Customer getCustomerInfo() {
		return customerRepository.getCustomer();
	}

}
