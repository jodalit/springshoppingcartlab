package com.alithya.shoppingcart.test.repository.implementation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alithya.shoppingcart.configuration.ShoppingOnlineRootApplicationContextConfig;
import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ShoppingOnlineRootApplicationContextConfig.class})
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public class ItemRepositoryImplementationTest {
	
	@Autowired
	private  ItemRepository itemRepository;
	
	@Test
	public void testInsertItem() {
		List<Item> items = itemRepository.getAllItems();
		assertThat(items.size(),  is(0));
	}
	
	@Test
	public void testGetAllItems() {
		List<Item> items = itemRepository.getAllItems();
		assertThat(items.size(),  is(0));
	}
}
