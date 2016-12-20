package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.CustomerRepository;

@Repository
public class CustomerRepositotyImplementation implements CustomerRepository {
	public static final String ID = "id";
	public static final String AVAILABLE_AMOUNT = "availableAmount";
	
	public static final String SQL_SELECT_CUSTOMER_INFO = "SELECT * FROM Person_Customer_view";
	public static final String SQL_UPDATE_AVAILABLE_AMOUNT = "UPDATE Customer SET customerAvailableAmount = :availableAmount WHERE customerId = :id";
	
	private JdbcTemplate jdbcTemplateShoppingCart;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public CustomerRepositotyImplementation (DataSource dataSouce) {
		jdbcTemplateShoppingCart = new JdbcTemplate(dataSouce);
	}
	
	@Override
	public Customer getCustomer() {
		Map<String, Object> params = new HashMap<>();
		
		//return jdbcTemplate.queryForObject(SQL_SELECT_CUSTOMER_INFO, params, new CustomerPersonMapper());
		return jdbcTemplateShoppingCart.queryForObject(SQL_SELECT_CUSTOMER_INFO, new CustomerPersonMapper());
	}

	@Override
	public boolean updateAmount(Double amount, Long customerId) {
		if (amount == null)
			return false;
		
		Map<String, Object> params = new HashMap<>();
		params.put(AVAILABLE_AMOUNT, amount);
		params.put(ID, customerId);
		
		//jdbcTemplate.update(SQL_UPDATE_AVAILABLE_AMOUNT, params);
		jdbcTemplateShoppingCart.update(SQL_UPDATE_AVAILABLE_AMOUNT, params);
		return true;
	}
	
	private static final class CustomerPersonMapper implements org.springframework.jdbc.core.RowMapper<Customer> {	
		
		
		public static final String CUSTOMER_AVAILABLE_AMOUNT = "customerAvailableAmount";
		public static final String CUSTOMER_ID = "customerId";
		public static final String PROFILE_ID = "profileId";
		public static final String CUSTOMER_PASSWORD = "customerPassword";
		public static final String CUSTOMER_CONNNECTION_NAME = "customerConnnectionName";
		public static final String CUSTOMER_NAME = "customerName";
		public static final String PERSON_ID = "personId";

		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			
			Person person = new Person();
			person.setPersonId(rs.getLong(PERSON_ID));
			person.setPersonName(rs.getString(CUSTOMER_NAME));
			person.setPersonConnectionName(rs.getString(CUSTOMER_CONNNECTION_NAME));
			person.setPersonPassword(rs.getString(CUSTOMER_PASSWORD));
			person.setProfile(rs.getInt(PROFILE_ID));
			
			customer.setCustomerId(rs.getLong(CUSTOMER_ID));
			customer.setCustomerAvailableAmount(rs.getDouble(CUSTOMER_AVAILABLE_AMOUNT));
			customer.setPerson(person);
						
			return customer;
		}
	}
}
