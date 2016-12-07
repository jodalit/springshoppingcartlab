package com.alithya.shoppingcart.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.alithya.shoppingcart"})
@EnableTransactionManagement
public class ShoppingOnlineRootApplicationContextConfig {
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	@Profile("test")
	public DataSource dataSourceForTest(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		
		return builder
				.setType(EmbeddedDatabaseType.DERBY)
				.addScript("db/sql/insert.sql")
				.build();
	}
	
	@Bean(name="dataSource")
	@Profile("prod")
	public DataSource dataSourceForProduction(){
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.pass"));
	
		return dataSource;
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
	
	@Bean
	@Profile("prod")
	public NamedParameterJdbcTemplate getJdbcTemplateForProduction(){
		
		return new NamedParameterJdbcTemplate(dataSourceForProduction());
	}
	
	@Bean(name = "transactionManager")
	@Profile("prod")
	public PlatformTransactionManager transactionManager(){
		
		return new DataSourceTransactionManager(dataSourceForProduction());
	}
}
