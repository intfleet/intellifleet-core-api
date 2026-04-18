package com.intellifleet.config;

import com.intellifleet.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.intellifleet.filter.JwtRequestFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		System.out.println("executing filterChain()......");
		security.csrf(AbstractHttpConfigurer::disable) // ✅ Non-deprecated lambda syntax
				.authorizeHttpRequests(request -> request
						.requestMatchers("/authenticate").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/console/**").permitAll()
						.requestMatchers("/api-docs/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
						.anyRequest()
						.authenticated()
				)
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.userDetailsService(jwtUserDetailsService);

		// For H2 console frame display
		security.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
		security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return security.build();
	}
}
