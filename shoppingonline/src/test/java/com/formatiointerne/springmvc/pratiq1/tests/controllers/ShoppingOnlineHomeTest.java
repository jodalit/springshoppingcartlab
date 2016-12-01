package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineHomeTest {
	@Autowired
	private ItemServiceImplementation itemService;
		
	@Autowired 
	MockHttpServletRequest request;
	
	@Mock
	private ItemServiceImplementation itemServiceMock;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		System.out.println("Beginning test with mocked HttpServletRequest ");
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("End test");
	}

	@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(itemService);
	}
	
	@Test
	public void verifyAllMockServices_areNotNull(){
		assertNotNull(itemServiceMock);
	}
	
	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
