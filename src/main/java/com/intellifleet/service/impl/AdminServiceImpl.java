package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiConstants;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.*;
import com.intellifleet.entity.ClientGroupChildEntity;
import com.intellifleet.entity.ClientGroupEntity;
import com.intellifleet.entity.CustomerMasterEntity;
import com.intellifleet.service.AdminService;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl extends GenericAutowiredService implements AdminService {

    @Autowired
    private FormService formService;

    @Override
    public ApiResponseEntity getGroupList() {
        List<ClientGroupEntity> list = genericRepository.findAll(ClientGroupEntity.class);
        List<ClientGroupDTO> dtoList = list.stream().map(mapStructUtils::toClientGroupDTO).toList();
        return new ApiResponseEntity(ApiHttpStatus.OK, dtoList);
    }

    @Override
    public ApiResponseEntity getClientGroupChildList(int customerClusterId) {
        List<ClientGroupChildEntity> list = genericRepository.findAll(ClientGroupChildEntity.class,
                String.format("customerClusterID = %s", customerClusterId));
        List<ClientGroupChildDTO> dtoList = list.stream().map(mapStructUtils::toClientGroupChildDTO).toList();
        return new ApiResponseEntity(ApiHttpStatus.OK, dtoList);
    }

    @Override
    public ApiResponseEntity getClientList(int customerClusterId) {
        List<CustomerMasterEntity> list = genericRepository.findAll(CustomerMasterEntity.class,
                String.format("customerClusterId = %s", customerClusterId));
        if(customerClusterId > 0) {
            list = list.stream().filter(f -> f.getCustomerClusterId() != null && f.getCustomerClusterId() == customerClusterId).toList();
        }
        return new ApiResponseEntity(ApiHttpStatus.OK, list.stream().map(mapStructUtils::toCustomerMasterDTO).toList());
    }






}
