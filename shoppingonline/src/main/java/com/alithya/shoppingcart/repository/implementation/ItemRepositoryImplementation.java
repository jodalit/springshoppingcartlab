package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Repository
//@Profile("prod")
public class ItemRepositoryImplementation implements ItemRepository {
	public static final String SELECT_FROM_ITEM = "SELECT * FROM Item";
	private static int nextId = 4;
	
	private JdbcTemplate jdbcTemplateShoppingCart;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ItemRepositoryImplementation (DataSource dataSouce) {
		jdbcTemplateShoppingCart = new JdbcTemplate(dataSouce);
	}
	
	
	public List<Item> getAllItems() {
		/*Map<String, Object> params = new HashMap<>();
		
		List<Item> result = jdbcTemplate.query(SELECT_FROM_ITEM, params, new ItemMapper());
		return result;*/
		return jdbcTemplate.query(SELECT_FROM_ITEM, new ItemMapper());
	}
	
	/*@Override
	public List<Item> getAllItems() {
		System.out.println("jdbcTemplateShoppingCart.query : " + jdbcTemplateShoppingCart.query(SELECT_FROM_ITEM, new ItemMapper()));
		return jdbcTemplateShoppingCart.query(SELECT_FROM_ITEM, new ItemMapper());
	}*/
	
	
	private static final class ItemMapper implements org.springframework.jdbc.core.RowMapper<Item> {
		public static final String ITEM_ID = "ITEMID";
		public static final String ITEM_NAME = "ITEMNAME";
		public static final String ITEM_DESCRIPTION = "ITEMDESCRIPTION";
		public static final String ITEM_PRICE = "ITEMPRICE";
		public static final String ITEM_EXPIREDATE = "ITEMEXPIREDATE";
		
		@Override
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item item = new Item();
			item.setItemId(rs.getLong(ITEM_ID));
			item.setItemName(rs.getString(ITEM_NAME));
			item.setDescription(rs.getString(ITEM_DESCRIPTION));
			item.setPrice(rs.getDouble(ITEM_PRICE));
			item.setExpireDate(LocalDate.now());
			
			return item;
		}
		
	} 
	
	
}
