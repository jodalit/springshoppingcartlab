package com.alithya.shoppingcart.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.alithya.shoppingcart.model.Basket;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.model.Person;

@Configuration
@EnableWebMvc
@ComponentScan({"com.alithya.shoppingcart.service", "com.alithya.shoppingcart.exception", "com.alithya.shoppingcart.repository", "com.alithya.shoppingcart.repository.implementation", "com.alithya.shoppingcart.model", "com.alithya.shoppingcart.configuration", "com.alithya.shoppingcart.controller", "com.alithya.shoppingcart.webservice.customer"})
public class ShoppingOnlineWebApplicationContextConfig extends WebMvcConfigurerAdapter {

	public static final String RESOURCES_DIRECTORY = "/resources/";
	public static final String RESOURCES_PATTERN = "/resources/**";
	public static final String PARAM_JSP = ".jsp";
	public static final String PARAM_WEB_INF_ALL_JS_PS = "/WEB-INF/allJSPs/";

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		
		InternalResourceViewResolver myResolver = new InternalResourceViewResolver();
		myResolver.setViewClass(JstlView.class);
		myResolver.setPrefix(PARAM_WEB_INF_ALL_JS_PS);
		myResolver.setSuffix(PARAM_JSP);
		
		return myResolver;
	}
	
	@Bean 
    public MappingJackson2JsonView jsonView() { 
       MappingJackson2JsonView jsonView = new       
    MappingJackson2JsonView(); 
       jsonView.setPrettyPrint(true); 
  
       return jsonView;  
    } 
	
	@Bean 
    public MarshallingView xmlView() { 
       Jaxb2Marshaller marshaller = new Jaxb2Marshaller(); 
       marshaller.setClassesToBeBound(Item.class); 
       marshaller.setClassesToBeBound(Basket.class);
       marshaller.setClassesToBeBound(Person.class);
       marshaller.setClassesToBeBound(Customer.class);
       MarshallingView xmlView = new MarshallingView(marshaller); 
       return xmlView; 
    }

	@Bean 
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) { 
       ContentNegotiatingViewResolver resolver = new       
    ContentNegotiatingViewResolver(); 
       resolver.setContentNegotiationManager(manager); 
     
       ArrayList<View> views = new ArrayList<View>(); 
       views.add(jsonView()); 
       views.add(xmlView()); 
     
       resolver.setDefaultViews(views); 
        
       return resolver; 
    }
	
	@Bean
	public CommonsMultipartResolver multiPartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_PATTERN).addResourceLocations(RESOURCES_DIRECTORY);
	}
	
}
