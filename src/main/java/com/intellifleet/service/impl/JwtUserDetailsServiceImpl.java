/**
 * 
 */
package com.intellifleet.service.impl;

import java.util.*;

import com.intellifleet.bean.CoreMatrixBean;
import com.intellifleet.bean.UserDetailsBean;
import com.intellifleet.entity.CoreMatrixView;
import com.intellifleet.entity.UserMasterEntity;
import com.intellifleet.repository.GenericRepository;
import com.intellifleet.service.GenericAutowiredService;
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
public class JwtUserDetailsServiceImpl extends GenericAutowiredService implements JwtUserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	GenericRepository genericRepository;

	@Override
	public UserContext loadUserByUsername(String username) throws UsernameNotFoundException {

		//Here db userUId is eqaual to spring security username.
		//Optional<UserMasterEntity> first = genericRepository.findAll(UserMasterEntity.class).stream().filter(f -> username.equalsIgnoreCase(f.getUserUid())).findFirst();
		List<CoreMatrixView> matrixViewList = genericRepository.findAll(CoreMatrixView.class, "userId = ?1", List.of(username));
		if(!matrixViewList.isEmpty()) {
			// TODO Auto-generated method stub

			CoreMatrixBean coreMatrix = mapStructUtils.toCoreMatrixBean(matrixViewList.getFirst());
			coreMatrix.setUserPassword(new BCryptPasswordEncoder().encode(coreMatrix.getUserPassword()));

			return new UserContext(coreMatrix, new ArrayList<>());
		}
		throw new UsernameNotFoundException("User not found with role: USER and username: " + username);
	}


	@Override
	public Map<String, Object> getAuthPayload(UserContext userContext, boolean isSessionListRequired) {
		Map<String, Object> claims = new HashMap<>();
		//claims.put("entityList", userContext.getEntityDetailsBean()); // Add anything you want here
		return claims;
	}

}
