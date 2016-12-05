package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
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
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineSearchItem;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.shoppingService;
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineSearchItemTest {
	@Mock
	private ItemService itemServiceMock;
	@Mock
	private shoppingService shoppingServiceMock;
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineSearchItem searchItem;
	
	@Autowired
	private ItemService itemService;
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request.setParameter("itemNameToFind", "centre");	
		searchItem = new ShoppingOnlineSearchItem();
		
		searchItem.setItemService(itemServiceMock);
		searchItem.setShoppingService(shoppingServiceMock);
	}

/*	@After
	public void tearDown() throws Exception {
		searchItem = null;
		request = null;
	}
	 
	@Test
	public void verifyAllMockServices_areNotNull(){
		assertNotNull(itemServiceMock);
		assertNotNull(shoppingServiceMock);
	}
	 
	@Test
	public void verifyExitance_ofItems_using_ConnexionnamePassword_providedBy_mockHttpServletRequest(){
		assertNotNull(itemService.getItemByNameDescription(request.getParameter("itemNameToFind")));
	}
 
	@Test //testGetshoppingonlinehomeclient()
	public void verifySizeOfItemSet_using_validParametres_providedBy_mockHttpServletRequest() throws Exception{ 
		if (itemService.getItemByNameDescription(request.getParameter("itemNameToFind")).size() !=0)
			assertTrue(true);
	}
		 
	@Test //testGetshoppingonlinehomeclient()
	public void verifySizeOfItemSet_using_noValidParametres_providedBy_mockHttpServletRequest() throws Exception{ 
		if (itemService.getItemByNameDescription(request.getParameter("itemNameToFind")).size() !=0)
			assertTrue(true);
	}
	*/
	//TODO you should have 2 tests, one for get and one for post
	@Test
	public void testGetResultSearchItem_for_HttpPost_withWordContainedInItemNameDescription() {
		String itemToFind =  (String)request.getAttribute("itemNameToFind");
		when(itemServiceMock.getItemByNameDescription(itemToFind)).thenReturn(new HashSet<>());
		String result = searchItem.getResultSearchItem(itemToFind, request);
		assertNotNull(result);
		//assertEquals("Display result page of search","shoppingonlinesearchresult", result);
		assertSame("Display result page of search","shoppingonlinesearchresult", result);
		// TODO try to test the values of the following items as per how they
		// are filled in the controller:
		// request.getSession().setAttribute("resultSearch", RESULTSEARCHTITLE);
		// request.getSession().setAttribute("numberItem", "0");
		// request.getSession().setAttribute("itemsforname", itemsforname);
		// request.getSession().setAttribute("sizeitemsforname",
		// sizeitemsforname);
	}
}
