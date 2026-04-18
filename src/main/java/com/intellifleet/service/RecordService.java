package com.intellifleet.service;

import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RecordService {
    public Map<String, Object> get(Long formId, Long id);
    public List<?> getList(String reqParams);
    public Map<String, Object> getListView(String reqParams);

    ApiHttpStatus saveOrUpdateRecord(SaveOrUpdateDTO saveOrUpdateDTO);
}
