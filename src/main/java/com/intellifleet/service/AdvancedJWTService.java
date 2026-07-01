package com.intellifleet.service;

import com.intellifleet.bean.UserContext;

import java.util.Map;

public interface AdvancedJWTService {
    String generateToken(UserContext userContext);

    Map<String, Object> validateAndRetrievedToken(String token);
}
