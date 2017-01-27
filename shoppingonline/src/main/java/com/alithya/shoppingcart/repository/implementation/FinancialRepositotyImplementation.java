package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.exception.BusinessException;
import com.alithya.shoppingcart.model.Customer;
import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.FinancialRepository;

@Repository
public class FinancialRepositotyImplementation implements FinancialRepository {
	public static final String PROD = "prod";
	public static final String ID = "id";
	public static final String AVAILABLE_AMOUNT = "availableAmount";
	
	public static final String SQL_SELECT_CUSTOMER_INFO = "SELECT * FROM Person_Customer_view";
	public static final String SQL_UPDATE_AVAILABLE_AMOUNT = "UPDATE Customer SET customerAvailableAmount = :availableAmount WHERE customerId = :id";
	
	private JdbcTemplate jdbcTemplateShoppingCart;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment environement;
	
	@Autowired
	public FinancialRepositotyImplementation (DataSource dataSouce) {
		jdbcTemplateShoppingCart = new JdbcTemplate(dataSouce);
	}
	
	@Override
	public Customer getCustomer() {
		String[] activeEnvironement = this.environement.getActiveProfiles();
		
		try{
			if (!PROD.equalsIgnoreCase(activeEnvironement[0])){
				return jdbcTemplate.queryForObject(SQL_SELECT_CUSTOMER_INFO, (HashMap) null , new CustomerPersonMapper());
			
			} else{
				List<Customer> l = jdbcTemplate.query(SQL_SELECT_CUSTOMER_INFO, new CustomerPersonMapper());
				
				return l.get(0);
			}
			
		} catch (Exception e) {
			throw new BusinessException("Database unavailable!");
		}	
	}

	@Override
	public boolean updateAmount(Double amount, Long customerId) throws SQLException{
		if (amount == null){
			throw new BusinessException("Amount should be number");
		}
			
		try {
			Map<String, Object> params = new HashMap<>();
			params.put(AVAILABLE_AMOUNT, amount);
			params.put(ID, customerId);
			
			jdbcTemplate.update(SQL_UPDATE_AVAILABLE_AMOUNT, params);
			return true;
		} catch (Exception e) {
			throw new BusinessException("Database unavailable!");
		}
		
	}
	
	private static final class CustomerPersonMapper implements org.springframework.jdbc.core.RowMapper<Customer> {			
		public static final String CUSTOMER_AVAILABLE_AMOUNT = "CUSTOMERAVAILABLEAMOUNT";
		public static final String CUSTOMER_ID = "CUSTOMERID";
		public static final String PROFILE_ID = "PROFILEID"; 
		public static final String CUSTOMER_PASSWORD = "PERSONPASSWORD";
		public static final String CUSTOMER_CONNNECTION_NAME = "PERSONCONNECTIONNAME";
		public static final String CUSTOMER_NAME = "PERSONNAME";
		public static final String PERSON_ID = "PERSONID";

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
