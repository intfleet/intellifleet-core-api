package com.intellifleet.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.intellifleet.bean.CoreMatrixBean;
import com.intellifleet.bean.UserContext;
import com.intellifleet.service.AdvancedJWTService;
import com.intellifleet.utils.AdvanceJWTTokenUtils;
import com.intellifleet.utils.JwtTokenUtil;
import com.intellifleet.utils.Utils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	FilterHelper filterHelper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AdvancedJWTService advancedJWTService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("executing doFilterInternal()......");
		System.out.println("RequestURI: " + request.getRequestURI());
		/*
		String array[] = filterHelper.getToken(log, request);
		String username = array[0];
		String jwtToken = array[1];
		
		filterHelper.validateToken(log, request, jwtToken, username);
		*/

		final String requestTokenHeader = request.getHeader("Authorization");
		String token = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			token = requestTokenHeader.substring(7);
			UserContext userContext = null;

			try {
				// Once we get the token validate it.
				if (StringUtils.isNotBlank(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
					if(AdvanceJWTTokenUtils.isJWEToken(token)) {
						Map<String, Object> claims = advancedJWTService.validateAndRetrievedToken(token);
						if(claims == null || claims.isEmpty()) {
							throw new RuntimeException("Claims is empty.");
						}
						String username = (String)claims.getOrDefault("username", "");
						log.info("User Name: {}", username);
						CoreMatrixBean coreMatrixBean = Utils.parseJSON((Map)claims.get("coreMatrix"), CoreMatrixBean.class);
						userContext = new UserContext(coreMatrixBean, List.of());
					} else {
						// This process used for Service Owner App
						String username = jwtTokenUtil.extractUsername(token);
						if(StringUtils.isBlank(username)) {
							throw new RuntimeException("Username not found.");
						}
						userContext = filterHelper.getUserContext(request, username);
					}
				}

				// if token is valid configure Spring Security to manually set authentication
				if (userContext != null) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
							new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the
					// Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				throw new RuntimeException("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				throw new RuntimeException("JWT Token has expired");
			} catch (MalformedJwtException e) {
				System.out.println(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		} else {
			log.warn("Missing request token header!");
		}
		chain.doFilter(request, response);
	}

}
