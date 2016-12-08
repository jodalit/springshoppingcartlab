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
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public DataSource dataSourceForProduction(){
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(){
		
		return new DataSourceTransactionManager(dataSourceForProduction());
	}
	
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplateForProduction(){
		
		return new NamedParameterJdbcTemplate(dataSourceForProduction());
	}

}