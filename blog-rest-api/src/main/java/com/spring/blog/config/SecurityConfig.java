package com.spring.blog.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private UserDetailsService userDetailsService;
	
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// This method will handle DB authentication using UserDetailsService and PasswordEncoder
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*
		 * Below code permit only all get method request by client
		 */
		http.csrf().disable().authorizeHttpRequests((authorize) ->
		// authorize.anyRequest().authenticated())
		authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
		.requestMatchers("/api/auth/**").permitAll()
		.anyRequest().authenticated())
		
		.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	// In memory authentication
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails abdul = User.builder().username("abdul").password(passwordEncoder().encode("abdul")).roles("USER")
//				.build();
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(abdul, admin);
//	}

}
