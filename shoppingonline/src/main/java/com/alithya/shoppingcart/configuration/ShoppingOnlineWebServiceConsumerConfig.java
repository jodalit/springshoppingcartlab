package com.alithya.shoppingcart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.alithya.shoppingcart.webservice.consumer.FinancialConsumer;

@Configuration
@ComponentScan({"localhost._8080.shoppingonline.financialschema", "com.alithya.shoppingcart.webservice.endpoint", "com.alithya.shoppingcart.webservice.customer", "com.alithya.shoppingcart.service", "com.alithya.shoppingcart.exception", "com.alithya.shoppingcart.repository", "com.alithya.shoppingcart.repository.implementation", "com.alithya.shoppingcart.model", "com.alithya.shoppingcart.configuration", "com.alithya.shoppingcart.controller"})
//@Import(value={ShoppingOnlineAPISecurityConfiguration.class})
public class ShoppingOnlineWebServiceConsumerConfig {
	@Bean
	public Jaxb2Marshaller marshaller(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("localhost._8080.shoppingonline.financialschema");
		return marshaller;
	}
	/*
	@Bean(name="wsrechargeconsumer")
	public FinancialConsumer financialRechargeConsumer(Jaxb2Marshaller marshaller) {
		FinancialConsumer consumer  = new FinancialConsumer();
		consumer.setDefaultUri("http://localhost:8080/shoppingonline/ws/wsrecharge.wsdl");
		consumer.setMarshaller(marshaller);
		consumer.setUnmarshaller(marshaller);
		return consumer;
	}
	*/
	
	@Bean
	public FinancialConsumer financialPurchaseConsumer(Jaxb2Marshaller marshaller) {
		FinancialConsumer consumer  = new FinancialConsumer();
		consumer.setDefaultUri("http://localhost:8080/shoppingonline/ws/wspurchase.wsdl");
		consumer.setMarshaller(marshaller);
		consumer.setUnmarshaller(marshaller);
		return consumer;
	}
}
