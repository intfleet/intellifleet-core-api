package com.intellifleet.exceptions;

import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.constants.AppConstants;
import com.intellifleet.dto.ApiResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ApiException> exception(InvalidEmailException exception, WebRequest webRequest) {
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

	@ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<ApiException> exception(NoRecordFoundException exception, WebRequest webRequest) {
		return ResponseEntity.status(exception.getStatus()).body(exception);
    }
	/*
	@ExceptionHandler(RecordIdNotFoundException.class)
    public ResponseEntity<ApiResponseEntity> exception(RecordIdNotFoundException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_NO_RECORD_FOUND_EXCEPTION, exception.getMessage());
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ApiResponseEntity> exception(DuplicateRecordException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_DUPLICATE_RECORD_EXCEPTION, exception.getMessage());
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ApiResponseEntity> exception(UserDisabledException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_USER_DISABLED_EXCEPTION, exception.getMessage());
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponseEntity> exception(InvalidCredentialsException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_INVALID_CREDENTIALS_EXCEPTION, exception.getMessage());
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(UserContextNotFoundException.class)
    public ResponseEntity<ApiResponseEntity> exception(UserContextNotFoundException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_USER_CONTEXT_NOT_FOUND_EXCEPTION, exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ApiResponseEntity> exception(UnsupportedMediaTypeException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_UNSUPPORTED_MEDIA_TYPE_EXCEPTION, exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@ExceptionHandler(RecordCanceledException.class)
    public ResponseEntity<ApiResponseEntity> exception(RecordCanceledException exception, WebRequest webRequest) {
    	ApiResponseEntity response = new ApiResponseEntity(AppConstants.RESP_STATUS_RECORD_CANCELED_EXCEPTION, exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }*/
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> exception(Exception exception, WebRequest webRequest) {
		ApiException apiException = new ApiException(exception.getMessage());
        return ResponseEntity.status(apiException.getStatus()).body(apiException);
    }

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiException> exception(ApiException exception, WebRequest webRequest) {
		return ResponseEntity.status(exception.getStatus()).body(exception);
	}
}
