/**
 * 
 */
package com.intellifleet.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.intellifleet.constants.ApiHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.CommonAppConstants;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.JwtRequestDTO;
import com.intellifleet.service.AuthenticationService;
import com.intellifleet.service.JwtUserDetailsService;
import com.intellifleet.utils.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author prady
 *
 */
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Override
	public ApiResponseEntity getAuthenticationDetails(JwtRequestDTO reqDTO, boolean isSessionListRequired)
			throws Exception {
		log.info("Started executing getAuthenticationDetails serviceImpl: {}", reqDTO);
		
		UserContext userContext = jwtUserDetailsService.loadUserByUsername(reqDTO.getUserName());
		final String token = jwtTokenUtil.generateToken(userContext);
		
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		/*	This are not required. We can add this info to token payload and retrieve from React by installing jwt-decode
			npm install jwt-decode
			const decoded = jwtDecode(token);
		*/
//		map.put("role", userContext.getUserDetailsBean().getRole());
//		map.put("user", userContext.getUserDetailsBean());
		
		Map<String, Object> additionalInfo = jwtUserDetailsService.getAuthPayload(userContext, isSessionListRequired);
		if(additionalInfo != null) {
			map.putAll(additionalInfo);
		}
		
		return new ApiResponseEntity(ApiHttpStatus.OK, map);
	}

}
