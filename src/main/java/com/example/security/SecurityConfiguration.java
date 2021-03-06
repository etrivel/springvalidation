package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

	@Configuration
	@EnableWebSecurity
	    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	       
	        protected void configure(AuthenticationManagerBuilder auth, HttpSecurity http) throws Exception {
//	            http.authorizeRequests()
//	                .antMatchers("/user").permitAll()
//	                .antMatchers("/confirm").permitAll();
	            auth.inMemoryAuthentication()
	                .withUser("hii")
	                .password("hiitoo")
	                .roles("USER");
	            
	        }
	        @Bean
	        public PasswordEncoder getPasswordEncoder() {
	        	  return NoOpPasswordEncoder.getInstance();
	        }
	        @Override
	        protected void configure(HttpSecurity http) throws Exception{
	        	http.authorizeRequests()
	        	    .antMatchers("/**").hasRole("USER")
	        	    .and().formLogin();
	        		
	        	
	        }
	}
