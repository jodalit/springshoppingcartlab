package com.alithya.shoppingcart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan({"localhost._8080.shoppingonline.financialschema", "com.alithya.shoppingcart.webservice.endpoint", "com.alithya.shoppingcart.service", "com.alithya.shoppingcart.exception", "com.alithya.shoppingcart.repository", "com.alithya.shoppingcart.repository.implementation", "com.alithya.shoppingcart.model", "com.alithya.shoppingcart.configuration", "com.alithya.shoppingcart.controller"})
//@Import(value={ShoppingOnlineAPISecurityConfiguration.class})
public class ShoppingOnlineWebServiceConfig extends WsConfigurerAdapter {
	public static final String RECHARGE_PORT = "wsrecharge";
	public static final String PURCHASE_PORT = "wspurchase";
	public static final String NAMESPACE = "http://localhost:8080/shoppingonline/financialschema";
		
	@Bean(name = "wsrecharge")
	public DefaultWsdl11Definition defaultWsdl11DefinitionRecharge(XsdSchema financialSchema){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName(RECHARGE_PORT);
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setSchema(financialSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "wspurchase")
	public DefaultWsdl11Definition defaultWsdl11DefinitionPurchase(XsdSchema financialSchema){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName(PURCHASE_PORT);
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setSchema(financialSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema financialSchema(){
		return new SimpleXsdSchema(new ClassPathResource("/schema/financialschema.xsd"));
	}
}
