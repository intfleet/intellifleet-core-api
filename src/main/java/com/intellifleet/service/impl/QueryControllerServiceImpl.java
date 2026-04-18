package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.QueryDTO;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.QueryControllerService;
import com.intellifleet.service.QueryService;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryControllerServiceImpl extends GenericAutowiredService implements QueryControllerService {

    @Autowired
    QueryService queryService;

    @Override
    public ApiResponseEntity saveOrUpdate(QueryDTO queryDTO) {
        UserContext userContext = Utils.getUserContext();

        ApiHttpStatus status = ApiHttpStatus.OK;
        if(queryDTO.getId() == null || queryDTO.getId() <= 0) {
            status = ApiHttpStatus.CREATED;
        }

        return new ApiResponseEntity(status, queryService.saveOrUpdate(queryDTO));
    }

    @Override
    public ApiResponseEntity getFormDown(Long id) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, queryService.getFormDown(id));
    }

    @Override
    public ApiResponseEntity getQueryBuilderInfo(Long formId) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, queryService.getQueryBuilderInfo(formId));
    }
}
