package com.skill.bucks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.skill.bucks.security.CustomUserDetailService;

//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorize -> 
//                authorize
//                .requestMatchers("/api/users")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//            )
//            .formLogin(Customizer.withDefaults());
//
//        return http.build();
//    }
//}


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
		@Autowired
		private CustomUserDetailService customUserDetailService;

	    @Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .anyRequest().authenticated()
	            )
	            .httpBasic(Customizer.withDefaults());
	        return http.build();
	    }
	    
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	    	
	    	auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	    	
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
}
