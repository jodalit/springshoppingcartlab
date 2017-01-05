package com.alithya.shoppingcart.configuration;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class ShoppingOnlineWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(ShoppingOnlineDispatcherServletConfigFile.class);
        webAppContext.register(ShoppingOnlineWebServiceConfig.class);
        
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		dispatcherServlet.setContextConfigLocation("");
		dispatcherServlet.setApplicationContext(webAppContext);
		
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", dispatcherServlet);
		
		appServlet.setLoadOnStartup(1);
		appServlet.setAsyncSupported(true);

		Set<String> mappingConflicts = appServlet.addMapping("/");

		MessageDispatcherServlet mds = new MessageDispatcherServlet();
		mds.setTransformWsdlLocations(true);
		mds.setApplicationContext(webAppContext);
		mds.setTransformWsdlLocations(true);
		mds.setContextConfigLocation("");

		ServletRegistration.Dynamic mdsServlet = servletContext.addServlet("mdsServlet", mds);
		mdsServlet.addMapping("/ws/*");
		mdsServlet.addMapping("*.wsdl");
		mdsServlet.setLoadOnStartup(2);
		mdsServlet.setAsyncSupported(true);
		
	}

}
