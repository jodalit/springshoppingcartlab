package com.formatiointerne.springmvc.pratiq1.tests.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;
import com.formatiointerne.springmvc.pratiq1.services.ItemServiceImplementation;
import com.formatiointerne.springmvc.pratiq1.services.ServicePersonImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ItemServiceImplementationTest {
	@Autowired
	private ItemService itemService;
	@Autowired
	MockHttpServletRequest request;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		itemService = new ItemServiceImplementation();
		request.setParameter("id", "15L");
		request.setParameter("name", "Alithya");
		request.setParameter("description", "Item orekj398 irewItem 1");
		request.setParameter("price", "14.40D");
		request.setParameter("expireDate", LocalDate.now().toString());
		
	}
	
	@Test
	public void verifyAllServices_areNotNull(){
		assertNotNull(itemService);
		assertNotNull(request);
	}
	
	@Test
	public void verifySetOfPeople() {
		assertNotNull(itemService.getItems());
	}
	
	@After
	public void tearDown() throws Exception {
		itemService = null;
		request = null;
	}

	@Test
	public void verify_createItem_returnPerson() {
		Item i = itemService.createItem("1", anyString(), anyString(), anyString(), anyString());
		assertNotNull(i);
	}
	
	@Test
	public void verify_modifyNameDescriptionPriceExpiredateItem_isTrueAndNotNull(){
		assertNotNull(itemService.modifyNameDescriptionPriceExpiredateItem(3L, "Alithya", "Alithya", "12.0", LocalDate.now().toString()));
		assertTrue(itemService.modifyNameDescriptionPriceExpiredateItem(3L, "Alithya", "Alithya", "12.0", LocalDate.now().toString()));
		
	}
	
	@Test
	public void verify_getItemByNameDescription_isNotNull(){
		assertNotNull(itemService.getItemByNameDescription(anyString()));
	}
	
	@Test
	public void verify_removeItem_of_anyLong_IsTrue(){
		assertTrue(itemService.removeItem(anyLong()));
	}
	
	@Test
	public void verify_removeItem_of_notValidLong_IsTrue(){
		assertNotSame(true, itemService.removeItem(Long.valueOf("the")));
	}
	
	@Test
	public void verify_getMaxItemId_isNotNull(){
		assertNotNull(itemService.getMaxItemId());
	}
	
	
	@Test
	public void verify_isNumeric_isNotNull(){
		assertNotNull(itemService.getMaxItemId());
	}
	
	
}
