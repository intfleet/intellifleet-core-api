package com.intellifleet.controller;

import com.intellifleet.api.RecordAPI;
import com.intellifleet.api.ResourceAPI;
import com.intellifleet.dto.ApiResponse;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.RecordControllerService;
import com.intellifleet.service.ResourceControllerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ResourceController implements ResourceAPI {

    @Autowired
    ResourceControllerService resourceControllerService;

    @Override
    public ResponseEntity<ApiResponse.ApiResult<Object>> getResourcesByCommodityId(Long commodityId) {
        ApiResponse.ApiResult<Object> result = resourceControllerService.getResourcesByCommodityId(commodityId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
