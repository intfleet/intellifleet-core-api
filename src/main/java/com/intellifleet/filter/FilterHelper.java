package com.intellifleet.filter;

import com.intellifleet.entity.CustomerMasterEntity;
import com.intellifleet.exceptions.EntityNotFoundException;
import com.intellifleet.repository.GenericRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.CommonAppConstants;
import com.intellifleet.service.JwtUserDetailsService;
import com.intellifleet.utils.JwtTokenUtil;
import com.intellifleet.utils.ParameterVerifier;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FilterHelper {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	GenericRepository genericRepository;
	
	public String[] getToken(Logger log, HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.extractUsername(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				throw new RuntimeException("JWT Token has expired");
			}
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}
		
		return new String[] {username, jwtToken};
	}
	
	public void validateToken(Logger logger, HttpServletRequest request, String jwtToken, String username) {
		
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserContext userContext = getUserContext(request, username);
			
			
			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userContext)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
	}
	
	public UserContext getUserContext(HttpServletRequest request, String username) {
//		return jwtUserDetailsService.loadUserByUsername(username);

		final String entityId = request.getHeader(CommonAppConstants.REQ_HEADER_ENTITY_ID);

		UserContext userContext = this.jwtUserDetailsService.loadUserByUsername(username);

		if(!StringUtils.isEmpty(entityId) && ParameterVerifier.getLong(entityId) > 0) {
			CustomerMasterEntity customerMaster = genericRepository.findById(CustomerMasterEntity.class, Long.parseLong(entityId));
			if(customerMaster == null) {
				log.error("Entity not found!!");
				throw new EntityNotFoundException();
			}
			//userContext.setCustomerMaster(customerMaster);
		}

		return userContext;
	}

}
