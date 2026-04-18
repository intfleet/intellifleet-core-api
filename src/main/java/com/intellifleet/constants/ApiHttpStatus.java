package com.intellifleet.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ApiHttpStatus {

    // --- 1xx Informational ---
    CONTINUE(HttpStatus.CONTINUE, "Continue"),
    SWITCHING_PROTOCOLS(HttpStatus.SWITCHING_PROTOCOLS, "Switching Protocols"),
    PROCESSING(HttpStatus.PROCESSING, "Processing"),
    EARLY_HINTS(HttpStatusCode.valueOf(103), "Early Hints"),

    // --- 2xx Success ---
    OK(HttpStatus.OK, "OK"),
    CREATED(HttpStatus.CREATED, "Created"),
    ACCEPTED(HttpStatus.ACCEPTED, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Non-Authoritative Information"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "No Content"),
    RESET_CONTENT(HttpStatus.RESET_CONTENT, "Reset Content"),
    PARTIAL_CONTENT(HttpStatus.PARTIAL_CONTENT, "Partial Content"),
    MULTI_STATUS(HttpStatus.MULTI_STATUS, "Multi-Status"),
    ALREADY_REPORTED(HttpStatus.ALREADY_REPORTED, "Already Reported"),
    IM_USED(HttpStatus.IM_USED, "IM Used"),

    // --- 3xx Redirection ---
    MULTIPLE_CHOICES(HttpStatus.MULTIPLE_CHOICES, "Multiple Choices"),
    MOVED_PERMANENTLY(HttpStatus.MOVED_PERMANENTLY, "Moved Permanently"),
    FOUND(HttpStatus.FOUND, "Found"),
    SEE_OTHER(HttpStatus.SEE_OTHER, "See Other"),
    NOT_MODIFIED(HttpStatus.NOT_MODIFIED, "Not Modified"),
    USE_PROXY(HttpStatusCode.valueOf(305), "Use Proxy"), // deprecated
    TEMPORARY_REDIRECT(HttpStatus.TEMPORARY_REDIRECT, "Temporary Redirect"),
    PERMANENT_REDIRECT(HttpStatus.PERMANENT_REDIRECT, "Permanent Redirect"),

    // --- 4xx Client Error ---
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    PAYMENT_REQUIRED(HttpStatus.PAYMENT_REQUIRED, "Payment Required"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "Request Timeout"),
    CONFLICT(HttpStatus.CONFLICT, "Conflict"),
    GONE(HttpStatus.GONE, "Gone"),
    LENGTH_REQUIRED(HttpStatus.LENGTH_REQUIRED, "Length Required"),
    PRECONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "Payload Too Large"),
    URI_TOO_LONG(HttpStatus.URI_TOO_LONG, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "Range Not Satisfiable"),
    EXPECTATION_FAILED(HttpStatus.EXPECTATION_FAILED, "Expectation Failed"),
    I_AM_A_TEAPOT(HttpStatus.I_AM_A_TEAPOT, "I'm a teapot"),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity"),
    LOCKED(HttpStatus.LOCKED, "Locked"),
    FAILED_DEPENDENCY(HttpStatus.FAILED_DEPENDENCY, "Failed Dependency"),
    TOO_EARLY(HttpStatus.TOO_EARLY, "Too Early"),
    UPGRADE_REQUIRED(HttpStatus.UPGRADE_REQUIRED, "Upgrade Required"),
    PRECONDITION_REQUIRED(HttpStatus.PRECONDITION_REQUIRED, "Precondition Required"),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, "Request Header Fields Too Large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "Unavailable For Legal Reasons"),

    // --- 5xx Server Error ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "Not Implemented"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "Bad Gateway"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "HTTP Version Not Supported"),
    VARIANT_ALSO_NEGOTIATES(HttpStatus.VARIANT_ALSO_NEGOTIATES, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(HttpStatus.INSUFFICIENT_STORAGE, "Insufficient Storage"),
    LOOP_DETECTED(HttpStatus.LOOP_DETECTED, "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(HttpStatusCode.valueOf(509), "Bandwidth Limit Exceeded"),
    NOT_EXTENDED(HttpStatus.NOT_EXTENDED, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "Network Authentication Required");

    private final HttpStatusCode status;
    private final String message;

    public int getCode() {
        return status.value();
    }

    public static ApiHttpStatus fromCode(int code) {
        return Arrays.stream(values())
                .filter(f -> f.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public static ApiHttpStatus fromHttpStatus(HttpStatus status) {
        return Arrays.stream(values()).filter(f -> f.status == status).findFirst().orElse(null);
    }

    public static ApiHttpStatus fromHttpStatus(HttpStatusCode status) {
        return Arrays.stream(values()).filter(f -> f.status == status).findFirst().orElse(null);
    }
}
