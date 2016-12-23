package com.alithya.shoppingcart.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class ShoppingOnlineWebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext ){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformSchemaLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema financialSchema){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("financialPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://localhost:8080/shoppingonline/financialschema");
		wsdl11Definition.setSchema(financialSchema);
		return wsdl11Definition;
		
	}
	
	@Bean
	public XsdSchema financialSchema(){
		return new SimpleXsdSchema(new ClassPathResource("/schema/financialschema.xsd"));
	}
}
