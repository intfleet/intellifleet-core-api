package com.intellifleet.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.InstrumentPacketForRedisDTO;
import com.intellifleet.service.GPSDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class GPSDataServiceImpl implements GPSDataService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ApiResponseEntity getGpsData() {

        return new ApiResponseEntity(ApiHttpStatus.OK, getAllGPSData());
    }

    @Override
    public List<InstrumentPacketForRedisDTO> getAllGPSData() {
        log.info("Started executing getAllGPSData()");
        List<InstrumentPacketForRedisDTO> result = new ArrayList<>();
        try {
            Set<String> keys = redisTemplate.keys("gps-data::*");
            log.info("keys: {}", keys);
            if (keys == null || keys.isEmpty()) {
                return Collections.emptyList();
            }

            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);
                log.info("value: {}", value);
                result.add(objectMapper.convertValue(value, InstrumentPacketForRedisDTO.class));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        log.info("result: {}", result);
        return result;
    }

}
