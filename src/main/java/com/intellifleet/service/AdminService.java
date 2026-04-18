package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;

public interface AdminService {

    ApiResponseEntity getGroupList();

    ApiResponseEntity getClientList(int customerClusterUid);

    ApiResponseEntity getClientGroupChildList(int customerClusterUid);

}
