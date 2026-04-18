package com.intellifleet.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityDetailsBean {
	public Long id;
	public String name;
	public Boolean isActive;
	public Long createdBy;	
	public Date createdDate;
	public Long modifiedBy;	
	public Date modifiedDate;	
}
