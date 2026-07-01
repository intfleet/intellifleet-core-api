package com.intellifleet.service;

import com.intellifleet.dto.ApiResponse;
import com.intellifleet.dto.ApiResponseEntity;

public interface ResourceControllerService {
    ApiResponse.ApiResult<Object> getResourcesByCommodityId(Long commodityId);
}
