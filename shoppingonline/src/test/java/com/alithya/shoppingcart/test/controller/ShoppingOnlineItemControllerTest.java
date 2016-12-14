package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.apt.model.IElementInfo;
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
	public void verifyGetAddItem() {
		
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.getAddItem(model);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_ADD_NEW_ITEM));
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnlineItemController.ADD_NEW_ITEM, model.get(shoppingOnlineItemController.MODEL_ADD_NEW_ITEM));
		assertNull(model.get(shoppingOnlineItemController.MODEL_SAVE_RESULT));
		assertSame("PAGE TO CREATE NEW ITEM", shoppingOnlineItemController.SHOPPING_ONLINE_NEWITEM, result);
	}
	
	@Test
	public void verifyGetSaveItem(){
		
		when(itemServiceMock.createItem("allo", "allo", "12.2", LocalDate.now().toString())).thenReturn(Long.valueOf(14));
		
		ModelMap model = new ModelMap();	
		String result = shoppingOnlineItemController.getSaveItem(model, "allo", "allo", "12.2", LocalDate.now().toString(), request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnlineItemController.SAVE_RESULT, model.get(shoppingOnlineItemController.MODEL_SAVE_RESULT));
		assertNotSame(0L, model.get(shoppingOnlineItemController.MODEL_NEW_ITEM_ID));
		assertSame(shoppingOnlineItemController.SHOPPING_ONLINE_LIST_OF_ALLITEMS, result);
	}
	
	@Test
	public void verifyShowDetailOfItems(){
		
		Item item = new Item(Long.valueOf(10), "Alithya", "Alithya Montreal", 125.45D, LocalDate.now());
		when(itemServiceMock.getItemById(Long.valueOf(10))).thenReturn(item);
		
		ModelMap model = new ModelMap();	
		String result = shoppingOnlineItemController.showDetailOfItems(Long.valueOf(10), model, request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_ITEM));
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_ITEM));
		assertEquals(item, model.get(shoppingOnlineItemController.MODEL_ITEM));
		assertSame("PAGE OF LISTE OF ITEMS", shoppingOnlineItemController.SHOPPING_ONLINE_DETAIL_OF_ITEM, result); 
	}
	
	@Test
	public void verifyGetAllItems(){
		
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.getAllItems(model, request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_ALL_ITEMS));
		assertEquals(shoppingOnlineItemController.ALL_ITEMS, model.get(shoppingOnlineItemController.MODEL_ALL_ITEMS));
		assertEquals("PAGE OF LISTE OF ITEMS", shoppingOnlineItemController.SHOPPING_ONLINE_LIST_OF_ALLITEMS, result);
	}
	

	@Test
	public void verifyUpdateDetailItemReturnValidPage(){
		
		when(itemServiceMock.getItemById(Long.valueOf(10))).thenReturn(new Item());
		
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.updateDetailItem(Long.valueOf(Long.MAX_VALUE), model, request);
		
		assertTrue(model.containsKey("item"));
		assertTrue(model.containsKey("updateitem"));
		assertEquals("PAGE OF UPDATING ITEM", shoppingOnlineItemController.SHOPPING_ONLINE_UPDATE_ITEM, result);
	}
	
	@Test
	public void verifyUpdateDetailItem_With_HTTP_GET_METHOD(){
		
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(18L, item);
		
		//itemServiceMock.setItems(items);
		when(itemServiceMock.getItemById(18L)).thenReturn(item);
		when(itemServiceMock.getItemsList()).thenReturn(items);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.updateDetailItem(Long.valueOf(18), model, request);
		
		assertTrue( request.getSession().getAttribute(shoppingOnlineItemController.MODEL_ITEMS) instanceof List<?>);
		assertTrue(model.containsKey(shoppingOnlineItemController.MODEL_UPDATE_ITEM));
		assertSame(item, model.get(shoppingOnlineItemController.MODEL_ITEM)); 		
		assertSame(shoppingOnlineItemController.UPDATE_ITEM, model.get(shoppingOnlineItemController.MODEL_UPDATE_ITEM));
		assertEquals(shoppingOnlineItemController.SHOPPING_ONLINE_UPDATE_ITEM, result);

	}
	
	@Test
	public void verifyUpdateDetailItem_With_HTTP_POST_METHOD(){
		
		Item item = new Item(Long.valueOf(17),"allo", "allo", 12.2D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(17L, item);
		
		//itemServiceMock.setItems(items);
		when(itemServiceMock.modifyNameDescriptionPriceExpiredateItem(Long.valueOf(17), "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString())).thenReturn(true);
		when(itemServiceMock.getItemById(Long.valueOf(17))).thenReturn(item);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnlineItemController.updateDetailItem(Long.valueOf(17), "alithya 700 gauchetiere", 
				"700 gauchetiere Montreal", "0.25",
				LocalDate.now().toString(),
				model, request);
		
		assertTrue( request.getSession().getAttribute(shoppingOnlineItemController.MODEL_ITEMS) instanceof List<?>);
		assertSame(item, model.get(shoppingOnlineItemController.MODEL_ITEM)); 		
		assertEquals(shoppingOnlineItemController.REDIRECT_LISTITEMS, result);

	}
	
	@Test
	public void verifyRemoveDetailItemWithSucces(){
		Item item = new Item(Long.valueOf(18),"allo", "allo", 12.2D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(18L, item);
		//itemServiceMock.setItems(items);
		when(itemServiceMock.removeItem(18L)).thenReturn(true);
		
		ModelMap model = new ModelMap();		
		String result = shoppingOnlineItemController.removeDetailItem(18L, model, request);
		
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
		assertSame(null, model.get("item")); 	//TODO try to assert the removed item
		assertEquals("PAGE OF REMOVING ITEM", shoppingOnlineItemController.REDIRECT_LISTITEMS , result);

	}
	

}
