package com.skill.bucks.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.skill.bucks.security.JwtAuthenticationEntryPoint;
import com.skill.bucks.security.JwtAuthenticationFilter;


@Configuration
@EnableMethodSecurity
//@EnableGlobalMethodSecurity
public class SecurityConfig {
		
		@Autowired
	    private JwtAuthenticationEntryPoint point;
		
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    
	    @Bean
	    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	//configuration
	    	
	    	http.csrf(csrf-> csrf.disable())
	    		.cors(cors-> cors.disable())
	    		.authorizeHttpRequests(
	    				auth-> 
	    						auth.requestMatchers("/home/**").authenticated()
	    							.requestMatchers(HttpMethod.GET).permitAll() //to allow all get requests
	    							.requestMatchers("/api/v1/auth/**").permitAll()
	    							.anyRequest().authenticated()
	    							)
	    		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
	    		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    	;
	    	
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    	
	    	return http.build();
	    }
	    

	    
	    
//	    
//	    @Bean
//	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(authorizeRequests ->
//	                authorizeRequests
//	                    .anyRequest().authenticated()
//	            )
//	            .httpBasic(Customizer.withDefaults());
//	        return http.build();
//	    }
//	    
	    
	    
	    
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	    
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
//	    @Bean
//	    public FilterRegistrationBean coresFilter() {
//	    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    	
//	    	CorsConfiguration corsConfiguration = new CorsConfiguration();
//	    	corsConfiguration.setAllowCredentials(true);
//	    	corsConfiguration.addAllowedOriginPattern("*");
//	    	corsConfiguration.addAllowedHeader("Authorization");
//	    	corsConfiguration.addAllowedHeader("Content-Type");
//	    	corsConfiguration.addAllowedHeader("Accept");
//	    	corsConfiguration.addAllowedHeader("POST");
//	    	corsConfiguration.addAllowedHeader("GET");
//	    	corsConfiguration.addAllowedHeader("DELETE");
//	    	corsConfiguration.addAllowedHeader("PUT");
//	    	corsConfiguration.addAllowedHeader("OPTIONS");
//	    	corsConfiguration.setMaxAge(3600L);
//	    	
//	    	source.registerCorsConfiguration("/**", corsConfiguration);
//	    	
//	    	FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//	    	return bean;
//	    	
//	    }
	    
}
