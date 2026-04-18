package com.intellifleet.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	FilterHelper filterHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("executing doFilterInternal()......");
		String array[] = filterHelper.getToken(log, request);
		String username = array[0];
		String jwtToken = array[1];
		
		filterHelper.validateToken(log, request, jwtToken, username);
		
		chain.doFilter(request, response);
	}

}
