/**
 * 
 */
package com.intellifleet.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.intellifleet.bean.UserContext;

/**
 * @author prady
 *
 */
public interface JwtUserDetailsService extends UserDetailsService {

	UserContext loadUserByUsername(String username) throws UsernameNotFoundException;
	Map<String, Object> getAuthPayload(UserContext userContext, boolean isSessionListRequired);
}
