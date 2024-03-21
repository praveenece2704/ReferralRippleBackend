//package com.example.main.securityconfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class UserSecurity {
//	
//	private static final String[] AUTH_WHITELIST = {
//	        "/api/**",
//	        "/swagger-resources/**",
//	        "/configuration/ui",
//	        "/configuration/security",
//	        "/swagger-ui.html",
//	        "/webjars/**",
//	        "/v3/api-docs/**",
//	        "/api/public/**",
//	        "/api/public/authenticate",
//	        "/actuator/*",
//	        "/swagger-ui/**"
//	};
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http.authorizeHttpRequests((authorize) -> authorize
//	    		
//	                    .requestMatchers(AUTH_WHITELIST).permitAll()
//	                    .anyRequest().permitAll()
//	            )
//	    
//	            .formLogin(Customizer.withDefaults())
//	            .csrf().disable();;
//	    return http.build();
//	}
//  
//}
