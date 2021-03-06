package com.alithya.shoppingcart.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alithya.shoppingcart.model.Person;
import com.alithya.shoppingcart.repository.PersonRepository;

@Repository
public class PersonRepositoryImplementation implements PersonRepository {
	public static final String SQL_SELECT_ALL_PEOPLE_PROFILE = "SELECT * FROM Person_Profile_view";
	
	private JdbcTemplate jdbcTemplateShoppingCart;
	
	@Autowired
	public PersonRepositoryImplementation (DataSource dataSouce) {
		jdbcTemplateShoppingCart = new JdbcTemplate(dataSouce);
	}
	
	@Override
	public List<Person> getAllPeople() {
		
		return jdbcTemplateShoppingCart.query(SQL_SELECT_ALL_PEOPLE_PROFILE, new PersonProfileMapper());
	}
	
	private static final class PersonProfileMapper implements org.springframework.jdbc.core.RowMapper<Person> {
		public static final String PERSON_PASSWORD = "PERSONPASSWORD";
		public static final String PERSON_CONNECTION_NAME = "PERSONCONNECTIONNAME";
		public static final String PERSON_SEX = "PERSONSEX";
		public static final String PERSON_NAME = "PERSONNAME";
		public static final String PERSON_ID = "PERSONID";
		
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setPersonId(rs.getLong(PERSON_ID));
			person.setPersonName(rs.getString(PERSON_NAME));
			person.setPersonSex(rs.getString(PERSON_SEX));
			person.setPersonConnectionName(rs.getString(PERSON_CONNECTION_NAME));
			person.setPersonPassword(rs.getString(PERSON_PASSWORD));
			person.setProfile(rs.getInt(PERSON_ID));
						
			return person;
		}
	}
}
