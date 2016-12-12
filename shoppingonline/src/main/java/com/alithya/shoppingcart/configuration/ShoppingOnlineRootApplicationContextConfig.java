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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
	
	@Bean(name=BEAN_DATA_SOURCE)
	public DataSource dataSourceForProduction(){
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty(JDBC_DRIVER_CLASS_NAME));
		dataSource.setUrl(env.getProperty(JDBC_URL));
		dataSource.setUsername(env.getProperty(JDBC_USERNAME));
		dataSource.setPassword(env.getProperty(JDBC_PASSWORD));
		
		return dataSource;
	}
	
	@Bean(name = BEAN_TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(){
		
		return new DataSourceTransactionManager(dataSourceForProduction());
	}
	
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplateForProduction(){
		
		return new NamedParameterJdbcTemplate(dataSourceForProduction());
	}

}
