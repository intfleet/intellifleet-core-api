package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface RecordControllerService {
    public ApiResponseEntity get(Long formId, Long id);
    public ApiResponseEntity getList(String reqParams);
    public ApiResponseEntity getListView(String reqParams);

    ApiResponseEntity saveOrUpdateRecord(SaveOrUpdateDTO saveOrUpdateDTO);

    ApiResponseEntity getGpsData();
}
