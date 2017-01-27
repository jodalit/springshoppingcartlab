package com.alithya.shoppingcart.configuration;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class ShoppingOnlineWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(ShoppingOnlineDispatcherServletConfigFile.class);
        
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		dispatcherServlet.setContextConfigLocation("");
		dispatcherServlet.setApplicationContext(webAppContext);
		
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", dispatcherServlet);
		
		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
	    securityFilter.addMappingForUrlPatterns(null, false, "/*");
		
		appServlet.setLoadOnStartup(2);
		appServlet.setAsyncSupported(true);

		Set<String> mappingConflicts = appServlet.addMapping("/");
        
		webAppContext.register(ShoppingOnlineWebServiceConsumerConfig.class);
        
		MessageDispatcherServlet mds = new MessageDispatcherServlet();
		mds.setTransformWsdlLocations(true);
		mds.setApplicationContext(webAppContext);
		mds.setTransformWsdlLocations(true);
		mds.setContextConfigLocation("");

		ServletRegistration.Dynamic mdsServlet = servletContext.addServlet("mdsServlet", mds);
		mdsServlet.addMapping("/ws/*");
		mdsServlet.addMapping("*.wsdl");
		mdsServlet.setLoadOnStartup(3);
		mdsServlet.setAsyncSupported(true);
		
       webAppContext.register(ShoppingOnlineWebServiceConsumerConfig.class);
        
		MessageDispatcherServlet mds2 = new MessageDispatcherServlet();
		mds2.setTransformWsdlLocations(true);
		mds2.setApplicationContext(webAppContext);
		mds2.setTransformWsdlLocations(true);
		mds2.setContextConfigLocation("");

		ServletRegistration.Dynamic mds2Servlet = servletContext.addServlet("mds2Servlet", mds2);
		mds2Servlet.addMapping("/ws/*");
		mds2Servlet.addMapping("*.wsdl");
		mds2Servlet.setLoadOnStartup(4);
		mds2Servlet.setAsyncSupported(true);
		
	}

}
