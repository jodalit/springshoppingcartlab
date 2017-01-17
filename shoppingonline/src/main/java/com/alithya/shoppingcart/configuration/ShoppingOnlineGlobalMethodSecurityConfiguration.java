package com.alithya.shoppingcart.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class ShoppingOnlineGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	/**
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("customer1")
			.password("password")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("admin")
			.roles("ADMIN");
	}*/
}
