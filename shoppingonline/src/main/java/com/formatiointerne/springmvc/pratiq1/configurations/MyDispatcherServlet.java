package com.formatiointerne.springmvc.pratiq1.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{
				MyWebAppContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}

}