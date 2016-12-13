package com.alithya.shoppingcart.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({"com.alithya.shoppingcart.service", "com.alithya.shoppingcart.repository", "com.alithya.shoppingcart.cache", "com.alithya.shoppingcart.repository.implementation", "com.alithya.shoppingcart.model", "com.alithya.shoppingcart.configuration", "com.alithya.shoppingcart.controller"})
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
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_PATTERN).addResourceLocations(RESOURCES_DIRECTORY);
	}
	
}
