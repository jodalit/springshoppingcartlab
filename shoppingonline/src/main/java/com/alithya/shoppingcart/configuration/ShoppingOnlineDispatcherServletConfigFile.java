package com.alithya.shoppingcart.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ShoppingOnlineDispatcherServletConfigFile extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{
				ShoppingOnlineWebApplicationContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}

}
