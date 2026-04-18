/**
 * 
 */
package com.intellifleet.bean;

import java.util.Collection;

import com.intellifleet.constants.CommonAppConstants;
import com.intellifleet.entity.CustomerMasterEntity;
import com.intellifleet.exceptions.UserContextNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


import lombok.Getter;
import lombok.Setter;

/**
 * @author prady
 *
 */
@Getter
@Setter
public class UserContext extends User {
	
	private static final long serialVersionUID = 441580371269041751L;
	
	UserDetailsBean userDetailsBean;
	CustomerMasterEntity customerMaster;
	
	private Long sessionId;
	
	public UserContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public UserContext(UserDetailsBean userDetailsBean, 
			Collection<? extends GrantedAuthority> authorities) {
		//Here db userUId is eqaual to spring security username.
		super(userDetailsBean.getUserUid(), userDetailsBean.getUserPassword(), authorities);
		this.userDetailsBean = userDetailsBean;
	}
	

	public Long getEntityId() {
		if(customerMaster != null) {
			return customerMaster.getRowWid();
		}
		return null;
	}

	public static UserContext getUserContext() {
		Authentication obj = SecurityContextHolder.getContext().getAuthentication();
		UserContext cntx = (UserContext)obj.getPrincipal();
		if(cntx == null) {
			throw new UserContextNotFoundException(CommonAppConstants.STATUS_MESSAGE.get(CommonAppConstants.RESP_STATUS_USER_CONTEXT_NOT_FOUND_EXCEPTION));
		}
		return cntx;
	}
}
