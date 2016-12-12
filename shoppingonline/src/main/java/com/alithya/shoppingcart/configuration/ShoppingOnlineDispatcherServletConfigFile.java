package com.alithya.shoppingcart.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ShoppingOnlineDispatcherServletConfigFile extends AbstractAnnotationConfigDispatcherServletInitializer {

	public static final String URL_FOR_MAPPING = "/";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[]{
				ShoppingOnlineRootApplicationContextConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{
				ShoppingOnlineWebApplicationContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{URL_FOR_MAPPING};
	}

}
