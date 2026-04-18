package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class RequestParamDTO {

	private Object params;
	
	private RequestParamDTO() {	}
	
	public static RequestParamDTO getInstance(String reqParams) {
		RequestParamDTO reqDet = null;
		try {
			reqDet = reqParams!= null ? new ObjectMapper().readValue(reqParams, RequestParamDTO.class) : new RequestParamDTO();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reqDet;
	}

	public static void main(String[] args) {
		//String json = "{\"serviceType\":\"FORM\",\"params\":{\"id\":1,\"name\":\"trip\",\"object\":\"trip\"}}";
		String json = "{\"serviceType\":\"FORM\",\"id\":1,\"name\":\"trip\",\"object\":\"trip\"}";
		RequestParamDTO req = RequestParamDTO.getInstance(json);

		System.out.println(req);
	}

}
