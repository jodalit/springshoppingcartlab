package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Repository
public class ItemRepositoryImplementation implements ItemRepository {
	
	public static final String PROD = "prod";
	public static final String PRAM_ITEM_ID = "id";
	public static final String PRAM_ITEM_PRICE = "price";
	public static final String PRAM_ITEM_DESCRIPTION = "description";
	public static final String PRAM_ITEM_NAME = "name";

	public static final String SQL_INSERT_ITEM = "INSERT INTO Item (itemId, itemName, itemDescription, itemPrice) VALUES (:id, :name, :description, :price)";
	public static final String SQL_INSERT_ITEM1 = "INSERT INTO Item (itemName, itemDescription, itemPrice) VALUES (:name, :description, :price)";
	public static final String SQL_UPDATE_ITEM = "UPDATE Item SET itemName = :name, itemDescription = :description, itemPrice = :price WHERE itemId = :id";
	public static final String SQL_DELETE_ITEM = "DELETE FROM Item WHERE itemId = :id";
	public static final String SQL_SELECT_ALL_ITEM = "SELECT * FROM Item ORDER BY itemid DESC";
	public static final String SQL_SELECT_LAST_ITEM = "SELECT * FROM Item ORDER itemid DESC LIMIT 1";
	
	@Autowired
	private Environment environement;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private JdbcTemplate jdbcTemplateShoppingCart;
	
	
	@Autowired
	public ItemRepositoryImplementation (DataSource dataSouce) {
		jdbcTemplateShoppingCart = new JdbcTemplate(dataSouce);
	}	
	
	public boolean insertItem(String itemName, String itemDescription, String itemPrice, String itemExpireDate){
		
		if ((itemName.isEmpty() && itemDescription.isEmpty()) && (itemPrice.isEmpty()|| itemPrice.trim().equals("0")))
			return false;
		
		String[] activeEnvironement = this.environement.getActiveProfiles();	
		Long itemId = 12L;
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_NAME, itemName);
		params.put(PRAM_ITEM_DESCRIPTION, itemDescription);
		params.put(PRAM_ITEM_PRICE, Double.valueOf( itemPrice));
		
		if (!PROD.equalsIgnoreCase(activeEnvironement[0])){
			params.put(PRAM_ITEM_ID, itemId);
			jdbcTemplateShoppingCart.update(SQL_INSERT_ITEM, params);
		} else{
			jdbcTemplate.update(SQL_INSERT_ITEM1, params);
		}
		
		return true;
	}
	
	@Override
	public List<Item> getAllItems() {
		String[] activeEnvironement = this.environement.getActiveProfiles();
		
		if (!PROD.equalsIgnoreCase(activeEnvironement[0])){
			return jdbcTemplateShoppingCart.query(SQL_SELECT_ALL_ITEM, new ItemMapper());
		} else{
			return jdbcTemplate.query(SQL_SELECT_ALL_ITEM, new ItemMapper());
		}
		
	}
	
	@Override
	public boolean updateItem(Long itemId, String itemName, String itemDescription, String itemPrice,
			String itemExpireDate) {
		
		if ((itemName.isEmpty() && itemDescription.isEmpty()) && (itemPrice.isEmpty()|| itemPrice.trim().equals("0")))
			return false;
		
		String[] activeEnvironement = this.environement.getActiveProfiles();
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_NAME, itemName);
		params.put(PRAM_ITEM_DESCRIPTION, itemDescription);
		params.put(PRAM_ITEM_PRICE, Double.valueOf( itemPrice));
		params.put(PRAM_ITEM_ID, itemId);
				
		if (!PROD.equalsIgnoreCase(activeEnvironement[0])){
			jdbcTemplateShoppingCart.update(SQL_UPDATE_ITEM, params);
		} else{
			jdbcTemplate.update(SQL_UPDATE_ITEM, params);
		}
		
		return true;
	}
	
	@Override
	public boolean deleteItem(Long itemId) {
		String[] activeEnvironement = this.environement.getActiveProfiles();
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_ID, itemId);
		
		if (!PROD.equalsIgnoreCase(activeEnvironement[0])){
			jdbcTemplateShoppingCart.update(SQL_DELETE_ITEM, params);
		} else{
			jdbcTemplate.update(SQL_DELETE_ITEM, params);
		}
				
		return true;
	}
	
	private static final class ItemMapper implements org.springframework.jdbc.core.RowMapper<Item> {
		public static final String ITEM_ID = "ITEMID";
		public static final String ITEM_NAME = "ITEMNAME";
		public static final String ITEM_DESCRIPTION = "ITEMDESCRIPTION";
		public static final String ITEM_PRICE = "ITEMPRICE";
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