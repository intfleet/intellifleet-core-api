package com.intellifleet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.CommonAppConstants;
import com.intellifleet.dto.FormDTO;
import com.intellifleet.exceptions.UserContextNotFoundException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@UtilityClass
@Slf4j
public class Utils {

    public UserContext getUserContext() {
        Authentication obj = SecurityContextHolder.getContext().getAuthentication();
        UserContext cntx = (UserContext)obj.getPrincipal();
        if(cntx == null) {
            throw new UserContextNotFoundException(CommonAppConstants.STATUS_MESSAGE.get(CommonAppConstants.RESP_STATUS_USER_CONTEXT_NOT_FOUND_EXCEPTION));
        }
        return cntx;
    }

    public <T> String stringifyJSON(T t) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
        return "";
    }

    public <T> T parseJSON(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, clazz);
        } catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
        return null;
    }

    public <T> List<T> parseArrayJSON(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(
                    content,
                    mapper.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return List.of(); // safer than returning null
        }
    }

    public <S,T> T copyProperties(Class<T> clazz, S s) {
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(s, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /*public Object copyProperties(Class<?> srcClazz, Object s) {
        try {
            Object t = srcClazz.newInstance();
            BeanUtils.copyProperties(s, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }*/
}
