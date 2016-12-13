package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.model.Item;
import com.alithya.shoppingcart.repository.ItemRepository;

@Repository
public class ItemRepositoryImplementation implements ItemRepository {
	
	public static final String PRAM_ITEM_ID = "id";
	public static final String PRAM_ITEM_PRICE = "price";
	public static final String PRAM_ITEM_DESCRIPTION = "description";
	public static final String PRAM_ITEM_NAME = "name";

	public static final String SQL_INSERT_ITEM = "INSERT INTO Item (itemName, itemDescription, itemPrice) VALUE (:name, :description, :price)";
	public static final String SQL_UPDATE_ITEM = "UPDATE Item SET itemName = :name, itemDescription = :description, itemPrice = :price WHERE itemId = :id";
	public static final String SQL_DELETE_ITEM = "DELETE FROM Item WHERE itemId = :id";
	public static final String SQL_SELECT_ALL_ITEM = "SELECT * FROM Item ORDER BY itemId DESC";
	public static final String SQL_SELECT_LAST_ITEM = "SELECT * FROM Item ORDER itemId DESC LIMIT 1";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public boolean insertItem(String itemName, String itemDescription, String itemPrice, String itemExpireDate){
		
		if ((itemName.isEmpty() && itemDescription.isEmpty()) && (itemPrice.isEmpty()|| itemPrice.trim().equals("0")))
			return false;
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_NAME, itemName);
		params.put(PRAM_ITEM_DESCRIPTION, itemDescription);
		params.put(PRAM_ITEM_PRICE, Double.valueOf( itemPrice));
		
		jdbcTemplate.update(SQL_INSERT_ITEM, params);
		
		return true;
	}
	
	@Override
	public List<Item> getAllItems() {
		Map<String, Object> params = new HashMap<>();
		
		return jdbcTemplate.query(SQL_SELECT_ALL_ITEM, params, new ItemMapper());
	}
	
	@Override
	public boolean updateItem(Long itemId, String itemName, String itemDescription, String itemPrice,
			String itemExpireDate) {
		
		if ((itemName.isEmpty() && itemDescription.isEmpty()) && (itemPrice.isEmpty()|| itemPrice.trim().equals("0")))
			return false;
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_NAME, itemName);
		params.put(PRAM_ITEM_DESCRIPTION, itemDescription);
		params.put(PRAM_ITEM_PRICE, Double.valueOf( itemPrice));
		params.put(PRAM_ITEM_ID, itemId);
		
		jdbcTemplate.update(SQL_UPDATE_ITEM, params);
		
		return true;
	}
	
	@Override
	public boolean deleteItem(Long itemId) {
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_ID, itemId);
		
		jdbcTemplate.update(SQL_DELETE_ITEM, params);
		
		return true;
	}
	
	private static final class ItemMapper implements org.springframework.jdbc.core.RowMapper<Item> {
		public static final String ITEM_ID = "ITEMID";
		public static final String ITEM_NAME = "ITEMNAME";
		public static final String ITEM_DESCRIPTION = "ITEMDESCRIPTION";
		public static final String ITEM_PRICE = "ITEMPRICE";
		public static final String ITEM_EXPIREDATE = "ITEMEXPIREDATE";
				
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
