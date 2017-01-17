package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;

import com.alithya.shoppingcart.configuration.ShoppingOnlineAPISecurityConfiguration;
import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineGlobalMethodSecurityConfiguration;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnLineItemController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class, ShoppingOnlineAPISecurityConfiguration.class, ShoppingOnlineGlobalMethodSecurityConfiguration.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class ShoppingOnlineItemControllerTest {
	@Mock
	private ItemService itemServiceMock;
	
	@Autowired
	MockHttpServletRequest request;
	
	private ShoppingOnLineItemController shoppingOnLineItemController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		shoppingOnLineItemController = new ShoppingOnLineItemController();
		shoppingOnLineItemController.setItemService(itemServiceMock);
	}
	
	@After
	public void tearDow() {
		SecurityContextHolder.clearContext();
	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void verifyGetAddItemWithoutAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin1", "admin1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.getAddItem(model);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_ADD_NEW_ITEM));
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnLineItemController.ADD_NEW_ITEM, model.get(shoppingOnLineItemController.MODEL_ADD_NEW_ITEM));
		assertNull(model.get(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		
		assertFalse(throwAccessDeniedException(("You don't have authority")));
	}
	
	@Test
	public void verifyGetAddItemWithAppropriateAuthority() {
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.getAddItem(model);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_ADD_NEW_ITEM));
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnLineItemController.ADD_NEW_ITEM, model.get(shoppingOnLineItemController.MODEL_ADD_NEW_ITEM));
		assertNull(model.get(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertSame("PAGE TO CREATE NEW ITEM", shoppingOnLineItemController.SHOPPING_ONLINE_NEWITEM, result);
	}
	
	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void verifyGetSaveItemWithoutAuthentication(){
		
		when(itemServiceMock.createItem("allo", "allo", "12.2", LocalDate.now().toString())).thenReturn(Long.valueOf(14));
		
		ModelMap model = new ModelMap();	
		String result = shoppingOnLineItemController.getSaveItem(model, "allo", "allo", "12.2", LocalDate.now().toString(), request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnLineItemController.SAVE_RESULT, model.get(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertNotSame(0L, model.get(shoppingOnLineItemController.MODEL_NEW_ITEM_ID));
		assertFalse(throwAuthenticationCredentialsNotFoundException("You don't authenticate"));
	}
	
	@Test
	public void verifyGetSaveItemWithAuthentication(){
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin", "admin");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		when(itemServiceMock.createItem("allo", "allo", "12.2", LocalDate.now().toString())).thenReturn(Long.valueOf(14));
		
		ModelMap model = new ModelMap();	
		String result = shoppingOnLineItemController.getSaveItem(model, "allo", "allo", "12.2", LocalDate.now().toString(), request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertSame(shoppingOnLineItemController.SAVE_RESULT, model.get(shoppingOnLineItemController.MODEL_SAVE_RESULT));
		assertNotSame(0L, model.get(shoppingOnLineItemController.MODEL_NEW_ITEM_ID));
		assertEquals("PAGE LIST OF ITEMS", shoppingOnLineItemController.SHOPPING_ONLINE_LIST_OF_ALLITEMS, result);
	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void verifyGetAllItemsWithoutAppropriateAuthority(){
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin1", "admin1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.getAllItems(model, request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_ALL_ITEMS));
		assertEquals(shoppingOnLineItemController.ALL_ITEMS, model.get(shoppingOnLineItemController.MODEL_ALL_ITEMS));
		assertFalse(throwAccessDeniedException(("You don't have authority on "+ result.toUpperCase() )));
	}
	
	@Test
	public void verifyGetAllItemsWithAppropriateAuthority(){
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.getAllItems(model, request);
		
		assertNotNull(result);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_ALL_ITEMS));
		assertEquals(shoppingOnLineItemController.ALL_ITEMS, model.get(shoppingOnLineItemController.MODEL_ALL_ITEMS));
		assertEquals("PAGE OF LISTE OF ITEMS", shoppingOnLineItemController.SHOPPING_ONLINE_LIST_OF_ALLITEMS, result);
	}

	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void verifyUpdateDetailItemWithoutAuthentication(){
		
		when(itemServiceMock.getItemById(Long.valueOf(10))).thenReturn(new Item());
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.updateDetailItem(Long.valueOf(Long.MAX_VALUE), model, request);
		
		assertTrue(model.containsKey("item"));
		assertTrue(model.containsKey("updateitem"));
		assertEquals("PAGE OF UPDATING ITEM", shoppingOnLineItemController.SHOPPING_ONLINE_UPDATE_ITEM, result);
		assertFalse(throwAuthenticationCredentialsNotFoundException("You don't authenticate"));
	}
	
	@Test
	public void verifyUpdateDetailItemWithAuthentication(){
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin", "admin");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		when(itemServiceMock.getItemById(Long.valueOf(10))).thenReturn(new Item());
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.updateDetailItem(Long.valueOf(Long.MAX_VALUE), model, request);
		
		assertTrue(model.containsKey("item"));
		assertTrue(model.containsKey("updateitem"));
		assertEquals("PAGE OF UPDATING ITEM", shoppingOnLineItemController.SHOPPING_ONLINE_UPDATE_ITEM, result);
	}
	
	@Test
	public void verifyUpdateDetailItemWith_HTTP_GET_METHOD(){
		
		Item item = new Item(Long.valueOf(18),"Training Center", "Alithya's Training Center", 78.27D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(18L, item);
		
		when(itemServiceMock.getItemById(18L)).thenReturn(item);
		when(itemServiceMock.getItemsList()).thenReturn(items);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.updateDetailItem(Long.valueOf(18), model, request);
		
		assertTrue( request.getSession().getAttribute(shoppingOnLineItemController.MODEL_ITEMS) instanceof List<?>);
		assertTrue(model.containsKey(shoppingOnLineItemController.MODEL_UPDATE_ITEM));
		assertSame(item, model.get(shoppingOnLineItemController.MODEL_ITEM)); 		
		assertSame(shoppingOnLineItemController.UPDATE_ITEM, model.get(shoppingOnLineItemController.MODEL_UPDATE_ITEM));
		assertEquals(shoppingOnLineItemController.SHOPPING_ONLINE_UPDATE_ITEM, result);

	}
	
	@Test
	public void verifyUpdateDetailItem_With_HTTP_POST_METHOD(){
		
		Item item = new Item(Long.valueOf(17),"allo", "allo", 12.2D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(17L, item);
		
		when(itemServiceMock.modifyNameDescriptionPriceExpiredateItem(Long.valueOf(17), "alithya 700 gauchetiere", "700 gauchetiere Montreal", "0.25", LocalDate.now().toString())).thenReturn(true);
		when(itemServiceMock.getItemById(Long.valueOf(17))).thenReturn(item);
		
		ModelMap model = new ModelMap();
		String result = shoppingOnLineItemController.updateDetailItem(Long.valueOf(17), "alithya 700 gauchetiere", 
				"700 gauchetiere Montreal", "0.25",
				LocalDate.now().toString(),
				model, request);
		
		assertTrue( request.getSession().getAttribute(shoppingOnLineItemController.MODEL_ITEMS) instanceof List<?>);
		assertSame(item, model.get(shoppingOnLineItemController.MODEL_ITEM)); 		
		assertEquals(shoppingOnLineItemController.REDIRECT_LISTITEMS, result);

	}
	
	@Test(expected=org.springframework.security.access.AccessDeniedException.class)
	public void verifyRemoveDetailItemWithoutAppropriateAuthority(){
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("customer1", "customer1");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Item item = new Item(Long.valueOf(18),"allo", "allo", 12.2D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(18L, item);
		
		when(itemServiceMock.removeItem(18L)).thenReturn(true);
		
		ModelMap model = new ModelMap();		
		String result = shoppingOnLineItemController.removeDetailItem(18L, model, request);
		
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
		assertSame(null, model.get("item")); 	
		assertEquals("PAGE OF REMOVING ITEM", shoppingOnLineItemController.REDIRECT_LISTITEMS , result);
		assertFalse(throwAccessDeniedException(("You don't have authority")));
	}
	
	@Test
	public void verifyRemoveDetailItemWithAppropriateAuthority(){	
		TestingAuthenticationToken authentication = new TestingAuthenticationToken("admin", "admin", "ROLE_ADMIN");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Item item = new Item(Long.valueOf(18),"allo", "allo", 12.2D, LocalDate.now());
		Map<Long, Item> items = new HashMap<>();
		items.put(18L, item);
		
		when(itemServiceMock.removeItem(18L)).thenReturn(true);
		
		ModelMap model = new ModelMap();		
		String result = shoppingOnLineItemController.removeDetailItem(18L, model, request);
		
		assertTrue( request.getSession().getAttribute("items") instanceof List<?>);
		assertSame(null, model.get("item")); 	
		assertEquals("PAGE OF REMOVING ITEM", shoppingOnLineItemController.REDIRECT_LISTITEMS , result);

	}
	
	private boolean throwAccessDeniedException(String result) {
        throw new org.springframework.security.access.AccessDeniedException(result);
    }
	
	private boolean throwAuthenticationCredentialsNotFoundException(String result) {
        throw new AuthenticationCredentialsNotFoundException(result);
    }

}
