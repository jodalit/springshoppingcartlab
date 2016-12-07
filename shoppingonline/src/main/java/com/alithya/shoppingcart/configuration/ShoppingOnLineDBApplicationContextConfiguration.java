package com.alithya.shoppingcart.configuration;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.alithya.shoppingcart"})
public class ShoppingOnLineDBApplicationContextConfiguration {
	@Bean
    public DataSource dataSource() {
        return getEmbeddedDatabaseBuilder()
            .addScript("classpath:sql/schema.sql")
            .addScript("classpath:sql/data.sql").build(); // or simply addDefaultScripts()
    }

    private EmbeddedDatabaseBuilder getEmbeddedDatabaseBuilder() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        /*databaseBuilder.setDataSourceFactory(new DataSourceFactory() {
            private final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            
            @Override
            public ConnectionProperties getConnectionProperties() {
                
            	return new ConnectionProperties() {
                    @Override
                    public void setDriverClass(Class<? extends Driver> driverClass) {
                        dataSource.setDriverClass(org.hsqldb.jdbcDriver.class);
                    }

                    @Override
                    public void setUrl(String url) {
                        dataSource.setUrl("jdbc:hsqldb:mem:testdb;sql.syntax_mys=true");
                    }

                    @Override
                    public void setUsername(String username) {
                        dataSource.setUsername("sa");
                    }

                    @Override
                    public void setPassword(String password) {
                        dataSource.setPassword("");
                    }
                };
            }

            @Override
            public DataSource getDataSource() {
                return dataSource;
            }
        });*/

        return databaseBuilder;
    }
}
