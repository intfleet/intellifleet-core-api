package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intellifleet.constants.ApiHttpStatus;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

//@Data
@Getter
@ToString
public class ApiResponseEntity {

	@JsonIgnore
	private ApiHttpStatus status;
	private List<String> messages;
	private Object data;

	public ApiResponseEntity(ApiHttpStatus status) {
		this.status = status;
	}
	
	public ApiResponseEntity(ApiHttpStatus status, String message) {
		this.status = status;
		this.messages = List.of(message);
	}

	public ApiResponseEntity(ApiHttpStatus status, List<String> messages) {
		this.status = status;
		this.messages = messages;
	}

	public ApiResponseEntity(ApiHttpStatus status, Object data) {
		this.status = status;
		this.data = data;
	}

	public ApiResponseEntity(ApiHttpStatus status, String message, Object data) {
		this.status = status;
		this.messages = List.of(message);
		this.data = data;
	}

	public ApiResponseEntity(ApiHttpStatus status, List<String> messages, Object data) {
		this.status = status;
		this.messages = messages;
		this.data = data;
	}

	public HttpStatusCode getStatus() {
		return status != null ? status.getStatus() : null;
	}
}
