package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse {

	public static enum ResponseStatus {
		SUCCESS,
		WARNING,
		ERROR
	}
//	private static final String STATUS_SUCCESS = "SUCCESS";
//	private static final String STATUS_WARNING = "WARNING";
//	private static final String STATUS_ERROR = "ERROR";

	@Getter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Builder
	public static class ApiResult<T> {
		private LocalDateTime timestamp;
		private ResponseStatus status;
		private T data;
		private List<String> messages;
		private List<String> warnings;
		private List<String> errors;
		private PageInfo pageInfo;
	}

	@Getter
	@AllArgsConstructor
	public static class PageInfo {
		private final int size;
		private final int number;
		private final long totalElements;
		private final int totalPages;
	}

	public static <T> ApiResult<T> success(T data) {
		return ApiResult.<T>builder()
				.timestamp(LocalDateTime.now())
				.status(ResponseStatus.SUCCESS)
				.data(data)
				.messages(List.of())
				.warnings(List.of())
				.errors(List.of())
				.build();
	}

	public static <T> ApiResult<T> success(T data, String message) {
		return ApiResult.<T>builder()
				.timestamp(LocalDateTime.now())
				.status(ResponseStatus.SUCCESS)
				.data(data)
				.messages(List.of(message))
				.warnings(List.of())
				.errors(List.of())
				.build();
	}

	/*	********************	WARNINGS	***********************	*/
	public static <T> ApiResult<T> warning(String warning) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.WARNING, null, Collections.emptyList(), List.of(warning), Collections.emptyList(), null);
	}

	public static <T> ApiResult<T> warning(List<String> warnings) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.WARNING, null, Collections.emptyList(), warnings, Collections.emptyList(), null);
	}

	public static <T> ApiResult<T> warning(T data, String warning) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.WARNING, data, Collections.emptyList(), List.of(warning), Collections.emptyList(), null);
	}

	public static <T> ApiResult<T> warning(T data, List<String> warnings) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.WARNING, data, Collections.emptyList(), warnings, Collections.emptyList(), null);
	}

	/*	********************	ERRORS	***********************	*/
	public static ApiResult<Object> error(List<String> errors) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.ERROR, null, Collections.emptyList(), Collections.emptyList(), errors,null);
	}

	public static ApiResult<Object> error(String error, String warning) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.ERROR, null, Collections.emptyList(), List.of(warning), List.of(error),null);
	}

	public static ApiResult<Object> error(List<String> errors, List<String> warnings) {
		return new ApiResult<>(LocalDateTime.now(), ResponseStatus.ERROR, null, Collections.emptyList(), warnings, errors,null);
	}

}