package com.alithya.shoppingcart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class ShoppingOnlineAPISecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private JdbcTemplate jdbcTemplateShoppingCart;
		
	/*private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT * FROM Person_Profile_view WHERE personConnectionName = ?");
	}*/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("customer1")
			.password("customer1")
			.authorities("ROLE_USER");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		//.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers("/showbasket").access("hasRole('ROLE_USER')")
		.antMatchers("/customer/**").access("hasRole('ROLE_USER')")
		.and()
		.formLogin()
		//.loginPage("/connection")
		.defaultSuccessUrl("/connection")
		.and()
		.logout().logoutSuccessUrl("/")
		.and()
		.authorizeRequests()
		.antMatchers( HttpMethod.GET,"/", "/resultsearchitem", "/connection").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/connection").permitAll()
		.anyRequest().permitAll()
		/*.authorizeRequests()
		.anyRequest().permitAll()*/
		;
	}
	
	/*@Override
	public void configure(WebSecurity web){
		web
			.ignoring()
			.antMatchers("/*.html");
	}*/
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http*/
			/*.authorizeRequests()
			.antMatchers("/addtobasketfromresultsearch/{itemId}").access("hasRole('ROLE_USER')")
			.antMatchers("/addtobasket/{itemId}").access("hasRole('ROLE_USER')")
			.antMatchers("/removefrombasket/{itemId}").access("hasRole('ROLE_USER')")
			.and()	
			.formLogin()
			.loginPage("/connect")
			.defaultSuccessUrl("/connection")
			.and()
			.logout().logoutSuccessUrl("/deconnection")
			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated();*/
			/*.authorizeRequests()
			.antMatchers("/showbasket").access("hasRole('ROLE_USER')")
			.and()
			.formLogin().defaultSuccessUrl("/resultsearchitem").and()
			.logout();
			//.loginPage("/connect")
			/*.defaultSuccessUrl("/connection")
			.and()
			.logout().logoutSuccessUrl("/")
			.and()
			.authorizeRequests()
			.antMatchers("/", "/connection", "/resultsearchitem").permitAll()
			.anyRequest().authenticated();*/
			
			
			
			/*
			.httpBasic().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).hasRole(ROLE_USER)
			.antMatchers(HttpMethod.POST).hasRole(ROLE_USER)
			.antMatchers(HttpMethod.PUT).hasRole(ROLE_USER)
			.antMatchers(HttpMethod.DELETE).hasRole(ROLE_USER)
			.anyRequest().authenticated();
			
	}*/
	
}
