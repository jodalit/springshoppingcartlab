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
public class ItemRepositoryImplementation implements ItemRepository {
	public static final String PRAM_ITEM_ID = "id";
	public static final String PRAM_ITEM_PRICE = "price";
	public static final String PRAM_ITEM_DESCRIPTION = "description";
	public static final String PRAM_ITEM_NAME = "name";

	public static final String SQL_UPDATE_ITEM = "UPDATE Item SET itemName = :name, itemDescription = :description, itemPrice = :price, itemExpireDate = :expireDate WHERE itemId = :id";
	public static final String SQL_DELETE_ITEM = "DELETE * FROM Item WHERE itemId = :id";
	public static final String SQL_SELECT_ALL_ITEM = "SELECT * FROM Item";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Item> getAllItems() {
		if (SQL_SELECT_ALL_ITEM == null)
			return null;
		
		Map<String, Object> params = new HashMap<>();
		
		return jdbcTemplate.query(SQL_SELECT_ALL_ITEM, params, new ItemMapper());
	}
	
	@Override
	public boolean updateItem(Long itemId, String itemName, String itemDescription, String itemPrice,
			String itemExpireDate) {
		
		if (SQL_UPDATE_ITEM == null)
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
		
		if (SQL_DELETE_ITEM == null)
			return false;
		
		Map<String, Object> params = new HashMap<>();
		params.put(PRAM_ITEM_ID, itemId);
				
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
