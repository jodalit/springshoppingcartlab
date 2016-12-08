package com.alithya.shoppingcart.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.ui.ModelMap;

import com.alithya.shoppingcart.configuration.ShoppingOnlineDispatcherServletConfigFile;
import com.alithya.shoppingcart.configuration.ShoppingOnlineWebApplicationContextConfig;
import com.alithya.shoppingcart.controller.ShoppingOnlineConnexionController;
import com.alithya.shoppingcart.controller.ShoppingOnlineSomeUtilitiesController;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.service.ItemService;
import com.alithya.shoppingcart.service.shoppingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineDispatcherServletConfigFile.class, ShoppingOnlineWebApplicationContextConfig.class})
@WebAppConfiguration
public class ShoppingOnlineSomeUtilitiesControllerTest {
	@Autowired
	MockHttpServletRequest request;
	
	@Mock
	private shoppingService shoppingServiceMock;

	@Mock
	private ItemService itemServiceMock;
	
	private ShoppingOnlineSomeUtilitiesController someUtilities;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		request.getSession().setAttribute("basket", shoppingServiceMock.getBasket());
		someUtilities = new ShoppingOnlineSomeUtilitiesController();
		someUtilities.setItemService(itemServiceMock);
		someUtilities.setShoppingService(shoppingServiceMock);
	}

	@Test
	public void verifyGetConnexion() {
		ModelMap model = new ModelMap();
		String CONNEXION = "Connexion to your on line store";
		String result =  someUtilities.getConnexion(model, request);
		
		assertNotNull(model.containsKey("connexion"));
		assertSame(someUtilities.SHOPPING_ONLINE_CONNEXION, result);
		
	}
	
	@Test
	public void verifySumitConnexion() {
		ModelMap model = new ModelMap();
		String result =  someUtilities.sumitConnexion(model);
		
		assertNotNull(model.containsKey("basketSize"));
		assertNotNull(model.containsKey("basket"));
		assertSame(someUtilities.SHOPPING_ONLINE_HOME_ADMIN, result);
		
		
	}
	
	@Test
	public void verifyGetShoppingOnlineHomeAdmin() {
		ModelMap model = new ModelMap();
		String result =  someUtilities.getShoppingOnlineHomeAdmin(model, request);
		
		assertNotNull(model.containsKey("connexion"));
		assertSame(someUtilities.SHOPPING_ONLINE_HOME_ADMIN, result);
		
	}

	@Test
	public void verifyGetBasket() {
		ModelMap model = new ModelMap();
		String result =  someUtilities.getBasket();
		
		assertSame(someUtilities.SHOPPING_ONLINE_BASKET, result);
		
	}
	
	@Test
	public void verifyGetNewUser() {
		ModelMap model = new ModelMap();
		String result =  someUtilities.getBasket();
		
		assertSame(someUtilities.SHOPPING_ONLINE_BASKET, result);
	}
	
	@Test
	public void verifyAddToBasketFromResultSearch() {
		Long id = Long.valueOf(12);
		Set<Item> basket = new HashSet<>();
		when(shoppingServiceMock.getBasket()).thenReturn(basket );
		someUtilities.infoBasket(id, request);
		
		String result =  someUtilities.addToBasketFromResultSearch(id, request);
		
		assertNotNull(request.getSession().getAttribute("basket"));
		assertSame( someUtilities.SHOPPING_ONLINE_SEARCH_RESULT, result);	
	}
	
	@Test
	public void verifyPayItems() {
		
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		when(shoppingServiceMock.getBasket()).thenReturn(basket);
		when(shoppingServiceMock.removeItemsToBasket()).thenReturn(true);
		
		String result = someUtilities.payItems(request);
		
		assertNull(request.getSession().getAttribute("basket"));
		assertSame(0,request.getSession().getAttribute("basketsize"));
		assertSame(0,request.getSession().getAttribute("baskettotal"));
		assertSame(someUtilities.SHOPPING_ONLINE_BASKET_RECEIVED, result);	
	}
	
	@Test
	public void verifyRemoveItemFromBasket() {
		Set<Item> basket = (Set<Item>) request.getSession().getAttribute("basket");
		Item item = new Item(Long.valueOf(14),"allo", "allo", 12.2D, LocalDate.now());
		basket.add(item);
		shoppingServiceMock.setBasket(basket);
		int basketSize = basket.size();
		request.getSession().setAttribute("basketsize", basketSize);
		
		when(shoppingServiceMock.getBasket()).thenReturn(basket);
		when(shoppingServiceMock.getTotalBasket()).thenReturn(900.0);
		when(shoppingServiceMock.removeItemToBasket(14L)).thenReturn(true);
		
		String result = someUtilities.removeItemFromBasket(14L, request);
		
		assertSame(0, request.getSession().getAttribute("basketsize"));
		assertSame(someUtilities.SHOPPING_ONLINE_BASKET, result);
	}
}