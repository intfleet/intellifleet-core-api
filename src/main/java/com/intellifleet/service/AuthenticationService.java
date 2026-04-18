package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.JwtRequestDTO;

/**
 * @author prady
 *
 */
public interface AuthenticationService {
	
	public ApiResponseEntity getAuthenticationDetails(JwtRequestDTO reqDTO, boolean isSessionListRequired) throws Exception;

}
