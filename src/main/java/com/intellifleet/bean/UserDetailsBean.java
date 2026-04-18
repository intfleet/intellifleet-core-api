package com.intellifleet.bean;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsBean {

	private Long id;

	private String userUid;

	private String userName;

	private String userPassword;

	private Long customerUid;
	
	private Boolean isDeleted;
	
	private Boolean isActive;
	
	private Long createdBy;	
	
	private Date createdDate;
	
	private Long modifiedBy;	
	
	private Date modifiedDate;	
}
