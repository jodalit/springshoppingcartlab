package com.alithya.shoppingcart.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.alithya.shoppingcart.repository"})
@PropertySource("classpath:/property/shoppingcart_info_db.properties")
@EnableTransactionManagement
public class ShoppingOnlineRootApplicationContextConfig {
	
	public static final String BEAN_TRANSACTION_MANAGER = "transactionManager";
	public static final String BEAN_DATA_SOURCE = "dataSource";
	private static final String JDBC_PASSWORD = "jdbc.password";
	public static final String JDBC_USERNAME = "jdbc.username";
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
	
	@Autowired
	private Environment env;
	
	@Bean(name=BEAN_DATA_SOURCE, destroyMethod="shutdown")
	@Profile("test")
	public DataSource dataSourceForTest(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		
		return builder
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8")
				.ignoreFailedDrops(true)
				.addScript("/db/sql/shoppingonline_create_item.sql")
				.addScripts("/db/sql/shoppingonline_create_profile.sql")
				.addScripts("/db/sql/shoppingonline_create_person.sql")
				.addScripts("/db/sql/shoppingonline_create_customer.sql")
				.addScripts("/db/sql/shoppingonline_create_person_profile_view.sql")
				.addScripts("/db/sql/shoppingonline_create_person_customer_view.sql")
				.build();
	}
	
	@Bean
	@Profile("test")
	public NamedParameterJdbcTemplate getJdbcTemplateForTes(){	
		return new NamedParameterJdbcTemplate(dataSourceForTest());
	}
	
	@Bean(name = "transactionManager")
	@Profile("test")
	public PlatformTransactionManager transactionManagerForTest(){
		
		return new DataSourceTransactionManager(dataSourceForTest());
	}

	
	@Bean(name=BEAN_DATA_SOURCE)
	@Profile("prod")
	public DataSource dataSourceForProduction(){
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty(JDBC_DRIVER_CLASS_NAME));
		dataSource.setUrl(env.getProperty(JDBC_URL));
		dataSource.setUsername(env.getProperty(JDBC_USERNAME));
		dataSource.setPassword(env.getProperty(JDBC_PASSWORD));
		
		return dataSource;
	}
	
	@Bean(name = BEAN_TRANSACTION_MANAGER)
	@Profile("prod")
	public PlatformTransactionManager transactionManager(){
		
		return new DataSourceTransactionManager(dataSourceForProduction());
	}
	
	@Bean
	@Profile("prod")
	public NamedParameterJdbcTemplate getJdbcTemplateForProduction(){
		
		return new NamedParameterJdbcTemplate(dataSourceForProduction());
	}

}
