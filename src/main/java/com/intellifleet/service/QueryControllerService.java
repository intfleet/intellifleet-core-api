package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.QueryDTO;

public interface QueryControllerService {
    ApiResponseEntity saveOrUpdate(QueryDTO queryDTO);

    ApiResponseEntity getFormDown(Long id);

    ApiResponseEntity getQueryBuilderInfo(Long formId);
}
