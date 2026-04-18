package com.intellifleet.controller;

import com.intellifleet.api.QueryAPI;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.QueryDTO;
import com.intellifleet.service.QueryControllerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class QueryController implements QueryAPI {

    @Autowired
    QueryControllerService queryControllerService;

    @Override
    public ResponseEntity<ApiResponseEntity> save(QueryDTO queryDTO) {
        ApiResponseEntity responseEntity = queryControllerService.saveOrUpdate(queryDTO);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getFormDown(Long id) {
        ApiResponseEntity responseEntity = queryControllerService.getFormDown(id);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getQueryBuilderInfo(Long formId) {
        ApiResponseEntity responseEntity = queryControllerService.getQueryBuilderInfo(formId);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }
}
