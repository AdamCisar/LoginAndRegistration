package com.example.RegistrationLogin.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    	
	@Bean
	protected DefaultSecurityFilterChain config(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/register").permitAll().and()
        .formLogin();
	
	    return http.build();
	  	    		
	  
	}

}