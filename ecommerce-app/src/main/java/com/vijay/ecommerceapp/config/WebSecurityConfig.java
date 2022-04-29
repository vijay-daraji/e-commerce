package com.vijay.ecommerceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admins/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/categories/**").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/categories/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/products/**").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/products/**").hasAuthority("ADMIN")
		.antMatchers("/cart/{userId}/**").access("@userSecurity.hasUserId(authentication, #userId)")
		.antMatchers(HttpMethod.GET,"/orders/**").hasAnyAuthority("USER","ADMIN") 
		.antMatchers("/orders/users/{userId}/**").access("@userSecurity.hasUserId(authentication, #userId)") 
		.antMatchers("/orders/{orderId}/users/{userId}/**").access("@userSecurity.hasUserId(authentication, #userId)")
		.antMatchers("/users/{userId}/**").access("@userSecurity.hasUserId(authentication, #userId)") 
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/users");
	}
	
	
}
