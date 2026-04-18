package com.intellifleet.controller;

import com.intellifleet.api.RecordAPI;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.GPSDataService;
import com.intellifleet.service.RecordControllerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RecordController implements RecordAPI {

    @Autowired
    RecordControllerService recordControllerService;

    @Override
    public ResponseEntity<ApiResponseEntity> get(Long formId, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recordControllerService.get(formId, id));
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getList(String reqParams) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getListView(String reqParams) {
        return ResponseEntity.status(HttpStatus.OK).body(recordControllerService.getListView(reqParams));
    }

    @Override
    public ResponseEntity<ApiResponseEntity> saveOrUpdateRecord(SaveOrUpdateDTO saveOrUpdateDTO) {
        ApiResponseEntity responseEntity = recordControllerService.saveOrUpdateRecord(saveOrUpdateDTO);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getGpsData() {
        ApiResponseEntity responseEntity = recordControllerService.getGpsData();
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }
}
