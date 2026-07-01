package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponse;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.*;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceControllerServiceImpl extends GenericAutowiredService implements ResourceControllerService {

    @Autowired
    ResourceService resourceService;

    @Override
    public ApiResponse.ApiResult<Object> getResourcesByCommodityId(Long commodityId) {
        UserContext userContext = Utils.getUserContext();
        return ApiResponse.success(resourceService.getResourcesByCommodityId(commodityId));
    }
}
