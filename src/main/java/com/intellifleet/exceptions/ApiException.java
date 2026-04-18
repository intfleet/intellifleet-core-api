package com.intellifleet.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intellifleet.constants.ApiHttpStatus;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private ApiHttpStatus status;
	private final List<String> errors;

	public ApiException(String error) {
		super(ApiHttpStatus.fromHttpStatus(ApiHttpStatus.INTERNAL_SERVER_ERROR.getStatus()).getMessage());
		status = ApiHttpStatus.fromHttpStatus(ApiHttpStatus.INTERNAL_SERVER_ERROR.getStatus());
		this.errors = List.of(error);
	}

	public ApiException(List<String> errors) {
		super(ApiHttpStatus.fromHttpStatus(ApiHttpStatus.INTERNAL_SERVER_ERROR.getStatus()).getMessage());
		status = ApiHttpStatus.fromHttpStatus(ApiHttpStatus.INTERNAL_SERVER_ERROR.getStatus());
		this.errors = errors;
	}

	public ApiException(ApiHttpStatus status, String error) {
		super(ApiHttpStatus.fromHttpStatus(status.getStatus()).getMessage());
		this.status = status;
		this.errors = List.of(error);
	}

	public ApiException(ApiHttpStatus status, List<String> errors) {
		super(ApiHttpStatus.fromHttpStatus(status.getStatus()).getMessage());
		this.status = status;
		this.errors = errors;
	}

	public ApiException(ApiHttpStatus status, String error, Throwable cause) {
		super(ApiHttpStatus.fromHttpStatus(status.getStatus()).getMessage(), cause);
		this.status = status;
		this.errors = List.of(error);
	}

	public HttpStatusCode getStatus() {
		return status != null ? status.getStatus() : null;
	}
}