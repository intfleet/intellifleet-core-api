/**
 * 
 */
package com.intellifleet.service.impl;

import java.util.*;

import com.intellifleet.bean.UserDetailsBean;
import com.intellifleet.entity.UserMasterEntity;
import com.intellifleet.repository.GenericRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intellifleet.bean.UserContext;
import com.intellifleet.service.JwtUserDetailsService;

/**
 * @author prady
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	GenericRepository genericRepository;

	@Override
	public UserContext loadUserByUsername(String username) throws UsernameNotFoundException {

		//Here db userUId is eqaual to spring security username.
		Optional<UserMasterEntity> first = genericRepository.findAll(UserMasterEntity.class).stream().filter(f -> username.equalsIgnoreCase(f.getUserUid())).findFirst();

		if(first.isPresent()) {
			// TODO Auto-generated method stub
			UserDetailsBean user = new UserDetailsBean();
			BeanUtils.copyProperties(first.get(), user);
			user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));

			return new UserContext(user, new ArrayList<>());
		}
		throw new UsernameNotFoundException("User not found with role: USER and username: " + username);
	}

	@Override
	public Map<String, Object> getAuthPayload(UserContext userContext, boolean isSessionListRequired) {
		Map<String, Object> claims = new HashMap<>();
		//claims.put("entityList", userContext.getEntityDetailsBean()); // Add anything you want here
		claims.put("entityList", List.of(Map.of("id", 1,
				"name", "IntelliFleet",
				"isActive", Boolean.TRUE)));
		return claims;
	}

}
