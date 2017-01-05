package com.alithya.shoppingcart.webservice.consumer;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import localhost._8080.shoppingonline.financialschema.PurchaseItemRequest;
import localhost._8080.shoppingonline.financialschema.PurchaseItemResponse;
import localhost._8080.shoppingonline.financialschema.RechargeRequest;
import localhost._8080.shoppingonline.financialschema.RechargeResponse;

public class FinancialConsumer extends WebServiceGatewaySupport{
	//private static final Logger lolg = LoggerFactory.get
	public PurchaseItemResponse doPurchaseItems(String basketReference){
		PurchaseItemRequest request = new PurchaseItemRequest();
		request.setCustomerBasketReference(basketReference);
		
		PurchaseItemResponse response = (PurchaseItemResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/shoppingonline/ws/purchaseItemRequest"));
		
		return response;
	}
	
	public RechargeResponse doRecharge(Double amount, Long customerId){
		RechargeRequest request = new RechargeRequest();
		request.setAmount(amount);
		request.setId(customerId);
		
		RechargeResponse response =  (RechargeResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback("http://localhost:8080/shoppingonline/ws/rechargeRequest"));
		
		return response;
	}
	
}
