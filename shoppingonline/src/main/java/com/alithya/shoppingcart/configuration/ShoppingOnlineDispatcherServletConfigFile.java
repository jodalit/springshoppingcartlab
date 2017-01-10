package com.alithya.shoppingcart.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ShoppingOnlineDispatcherServletConfigFile extends AbstractAnnotationConfigDispatcherServletInitializer {

	//public static final String WS_URL_FOR_MAPPING = "/ws/*";
	public static final String URL_FOR_MAPPING = "/";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[]{
				ShoppingOnlineRootApplicationContextConfig.class,
				ShoppingOnlineAPISecurityConfiguration.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{
				ShoppingOnlineWebApplicationContextConfig.class 
				//ShoppingOnlineAPISecurityConfiguration.class,
				//ShoppingOnlineWebSecurityConfiguration.class//, ShoppingOnlineWebServiceConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{URL_FOR_MAPPING};
	}
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);    
        servletContext.setInitParameter("spring.profiles.active", "prod");
    }
	
}
