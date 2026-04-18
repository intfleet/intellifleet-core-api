package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.InstrumentPacketForRedisDTO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface GPSDataService {
    ApiResponseEntity getGpsData();

    List<InstrumentPacketForRedisDTO> getAllGPSData();

}
