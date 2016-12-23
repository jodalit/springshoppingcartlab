package com.alithya.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.service.PaiementService;

@Endpoint
public class PaiementEndPoint {
	private static final String NAMESAPCE_URI ="http://localhost:8080/shoppingonline/wspayment";
	
	@Autowired
	private PaiementService paiementService;
	
	@PayloadRoot(namespace= NAMESAPCE_URI, localPart="getAvailableAmountRequest")
	@ResponsePayload
	public Customer getAvailableAmountResponse (@RequestPayload Customer customer){
		return customer;
		
	}
	

	public void setPaiementService(PaiementService paiementService) {
		this.paiementService = paiementService;
	}
	
}
