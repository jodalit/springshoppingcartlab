package com.formatiointerne.springmvc.pratiq1.tests.controllers;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import com.formatiointerne.springmvc.pratiq1.configurations.MyDispatcherServlet;
import com.formatiointerne.springmvc.pratiq1.configurations.MyWebAppContextConfig;
import com.formatiointerne.springmvc.pratiq1.controllers.ShoppingOnlineItem;
import com.formatiointerne.springmvc.pratiq1.datamodels.Item;
import com.formatiointerne.springmvc.pratiq1.services.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={MyDispatcherServlet.class, MyWebAppContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineItemTest {
	@Mock
	private ItemService itemServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineItem shoppingOnlineItemController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shoppingOnlineItemController = new ShoppingOnlineItem();
		shoppingOnlineItemController.setItemService(itemServiceMock);
	//	request.setParameter("personConnexion", "admin");
	 
	}

	/*@After
	public void tearDown() throws Exception {
		request = null;
		ctlItemService = null;
	}*/

	@Test
	public void verifyGetAddItem_toDisplay_pageInvoqued() {
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.addItem(model);
		assertNotNull(result);
		assertSame("PAGE TO CREATE NEW ITEM", "shoppingonlinenewitem", result);
	}
	
	@Test
	public void verifygetSaveItem_toDisplay_pageInvoqued(){
		Item item = new Item();
		when(itemServiceMock.getMaxItemId()).thenReturn(new Long(12));
		when(itemServiceMock.createItem(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(item);
		ModelMap model = new ModelMap();
				
		String result = shoppingOnlineItemController.getSaveItem(model, "allo", "allo", "12.2", LocalDate.now().toString(), request);
		assertNotNull(result);
		assertTrue(model.containsKey("saveresult"));
		assertTrue(model.containsKey("Itemnumero"));
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
		assertSame("PAGE TO SAVE NEW ITEM", "shoppingonlinelistofallitems", result);
		//TODO verify the value of the new element you inserted
		
	}
	
	@Test
	public void verifyShowDetailOfItems_toDisplay_pageInvoqued(){
		ModelMap model = new ModelMap();	
		//TODO Mock when itemService.getItemById(itemId) ,maybe use .thenAnswer and add an item in Model and see the returned result
		String result = shoppingOnlineItemController.showDetailOfItems(Long.valueOf(Long.MAX_VALUE), model, request);
		assertNotNull(result);
		assertTrue(model.containsKey("item"));
		assertSame("PAGE OF LISTE OF ITEMS", "shoppingonlinedetailofitem", result);
		//TODO see if it possible to test the returned item
		//assertEquals("PAGE OF LISTE OF ITEMS", "shoppingonlinedetailofitem", result);
	}
	
	@Test
	public void verifyGetAllItems_toDisplay_pageInvoqued(){
		ModelMap model = new ModelMap();
		//TODO Maybe use .thenAnswer and add a few items in "allItems"   and see the returned result
		String result = shoppingOnlineItemController.getAllItems(model, request);
		assertNotNull(result);
		assertTrue(model.containsKey("allitems"));
	//	assertSame("PAGE OF LISTE OF ITEMS", "shoppingonlinelistofallitems", result);
		assertEquals("PAGE OF LISTE OF ITEMS", "shoppingonlinelistofallitems", result);
	}
	

	@Test
	public void verifyUpdateDetailItem_toDisplay_pageInvoqued_witHttpGET(){
		ModelMap model = new ModelMap();
		//TODO mock when service.getItemById and maybe try to verify the updated item new value
		String result = shoppingOnlineItemController.updateDetailItem(Long.valueOf(Long.MAX_VALUE), model, request);
		//assertNotNull(result);
		assertTrue(model.containsKey("item"));
		assertTrue(model.containsKey("updateitem"));
	//	assertSame("PAGE OF UPDATING ITEM", "shoppingonlineupdateitem", result);
		assertEquals("PAGE OF UPDATING ITEM", "shoppingonlineupdateitem", result);
	}
	
	@Test
	public void verifyUpdateDetailItem_toDisplay_pageInvoqued_witHttpPOST(){
		when(itemServiceMock.modifyNameDescriptionPriceExpiredateItem((Long)Long.MAX_VALUE, "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString())).thenReturn(true);
		ModelMap model = new ModelMap();
				
		String result = shoppingOnlineItemController.updateDetailItem((Long)Long.MAX_VALUE, "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString(), model, request);
		//assertNotNull(result);
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
	//	assertSame("PAGE OF UPDATING ITEM AFTER POST", "redirect:/listitems", result);
		assertEquals("PAGE OF UPDATING ITEM AFTER POST", "redirect:/listitems", result);
		//TODO try to assert the updated value
	}
	
	@Test
	public void verifyRemoveDetailItem_toDisplay_pageInvoqued(){
		when(itemServiceMock.removeItem((Long)Long.MAX_VALUE)).thenReturn(true);
		ModelMap model = new ModelMap();
				
		String result = shoppingOnlineItemController.removeDetailItem((Long)Long.MAX_VALUE, model, request);
		//assertNotNull(result);
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
	//	assertSame("PAGE OF REMOVING ", "redirect:/listitems", result);
		assertEquals("PAGE OF REMOVING ITEM", "redirect:/listitems", result);
		//TODO try to assert the removed item
	}
	

}
