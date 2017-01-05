package com.alithya.shoppingcart.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alithya.shoppingcart.service.FinancialService;

import localhost._8080.shoppingonline.financialschema.PurchaseItemRequest;
import localhost._8080.shoppingonline.financialschema.PurchaseItemResponse;
import localhost._8080.shoppingonline.financialschema.RechargeRequest;
import localhost._8080.shoppingonline.financialschema.RechargeResponse;

@Endpoint
public class FinancialEndpoint {
	private static final String RECHARGE_NAMESPACE_URI = "http://localhost:8080/shoppingonline/ws/financialschema/rechargeaccount";
	private static final String PURCHASE_NAMESPACE_URI = "http://localhost:8080/shoppingonline/ws/financialschema/purchaseitem";
	
	@Autowired
	private FinancialService financialServiceShoppingOnLine;

	@PayloadRoot(namespace=RECHARGE_NAMESPACE_URI, localPart="rechargeRequest")
	@ResponsePayload
	public RechargeResponse getRechargeCustomerAccount(@RequestPayload RechargeRequest request){
	
		RechargeResponse response = new RechargeResponse();
		response.setResponse(financialServiceShoppingOnLine.recharge(request.getAmount(),request.getId()));
		
		return response;
	}
	
	@PayloadRoot(namespace=PURCHASE_NAMESPACE_URI, localPart="purchaseItemRequest")
	@ResponsePayload
	public PurchaseItemResponse getPurchaseItems(@RequestPayload PurchaseItemRequest request){
		
		PurchaseItemResponse response = new PurchaseItemResponse();		
		response.setResponse(financialServiceShoppingOnLine.purchaseItem(request.getCustomerBasketReference()));
		
		return null;
	}
	

	public void setFinancialServiceShoppingOnLine(FinancialService financialServiceShoppingOnLine) {
		this.financialServiceShoppingOnLine = financialServiceShoppingOnLine;
	}
	
}
