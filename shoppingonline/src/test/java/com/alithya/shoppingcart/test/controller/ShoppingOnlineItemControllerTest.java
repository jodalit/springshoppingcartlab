package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.ui.ModelMap;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineItemController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineItemControllerTest {
	@Mock
	private ItemService itemServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnlineItemController shoppingOnlineItemController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shoppingOnlineItemController = new ShoppingOnlineItemController();
		shoppingOnlineItemController.setItemService(itemServiceMock);
	}

	@Test
	public void verifyGetAddItemReturnValidPage() {
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.addItem(model);
		assertNotNull(result);
		assertSame("PAGE TO CREATE NEW ITEM", "shoppingonlinenewitem", result);
	}
	
	@Test
	public void verifygetSaveItemReturnValidPage(){
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
	public void verifyShowDetailOfItemsReturnValidPage(){
		ModelMap model = new ModelMap();	
		//TODO Mock when itemService.getItemById(itemId) ,maybe use .thenAnswer and add an item in Model and see the returned result
		String result = shoppingOnlineItemController.showDetailOfItems(Long.valueOf(Long.MAX_VALUE), model, request);
		assertNotNull(result);
		assertTrue(model.containsKey("item"));
		assertSame("PAGE OF LISTE OF ITEMS", "shoppingonlinedetailofitem", result);
		//TODO see if it possible to test the returned item 
	}
	
	@Test
	public void verifyGetAllItemsReturnValidPage(){
		ModelMap model = new ModelMap();
		//TODO Maybe use .thenAnswer and add a few items in "allItems"   and see the returned result
		String result = shoppingOnlineItemController.getAllItems(model, request);
		assertNotNull(result);
		assertTrue(model.containsKey("allitems"));
		assertEquals("PAGE OF LISTE OF ITEMS", "shoppingonlinelistofallitems", result);
	}
	

	@Test
	public void verifyUpdateDetailItemReturnValidPage(){
		ModelMap model = new ModelMap();
		//TODO mock when service.getItemById and maybe try to verify the updated item new value
		String result = shoppingOnlineItemController.updateDetailItem(Long.valueOf(Long.MAX_VALUE), model, request);
		assertTrue(model.containsKey("item"));
		assertTrue(model.containsKey("updateitem"));
		assertEquals("PAGE OF UPDATING ITEM", "shoppingonlineupdateitem", result);
	}
	
	@Test
	public void verifyUpdateDetailItemWithSucces(){
		when(itemServiceMock.modifyNameDescriptionPriceExpiredateItem((Long)Long.MAX_VALUE, "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString())).thenReturn(true);
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.updateDetailItem((Long)Long.MAX_VALUE, "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString(), model, request);
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
		assertEquals("PAGE OF UPDATING ITEM AFTER POST", "redirect:/listitems", result);
		//TODO try to assert the updated value
	}
	
	@Test
	public void verifyRemoveDetailItemWithSucces(){
		when(itemServiceMock.removeItem((Long)Long.MAX_VALUE)).thenReturn(true);
		ModelMap model = new ModelMap();
				
		String result = shoppingOnlineItemController.removeDetailItem((Long)Long.MAX_VALUE, model, request);
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
	//	
		assertEquals("PAGE OF REMOVING ITEM", "redirect:/listitems", result);
		//TODO try to assert the removed item
	}
	

}
