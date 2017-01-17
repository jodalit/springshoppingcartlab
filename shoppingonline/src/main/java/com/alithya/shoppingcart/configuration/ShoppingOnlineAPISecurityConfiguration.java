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
public class ShoppingOnlineAPISecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("customer1")
			.password("customer1")
			.authorities("ROLE_USER").and()
			.withUser("admin")
			.password("admin")
			.authorities("ROLE_ADMIN");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers(HttpMethod.POST, "/customer/**").access("hasRole('ROLE_USER')")
		.antMatchers(HttpMethod.POST, "/removeitem/**", "/updateitem/", "/item/**","/saveitem" , "/additem").access("hasRole('ROLE_ADMIN')")
		.and()
		.formLogin()
		.defaultSuccessUrl("/connection")
		.and()
		.logout().logoutSuccessUrl("/")
		.and()
		.authorizeRequests()
		.antMatchers( HttpMethod.GET,"/", "/resultsearchitem", "/connection").permitAll()
		.antMatchers(HttpMethod.POST, "/customer/**").access("hasRole('ROLE_USER')")
		.antMatchers(HttpMethod.POST, "/removeitem/**", "/updateitem/**", "/item/**","/saveitem" , "/additem").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();
	}
	
	
	
}
