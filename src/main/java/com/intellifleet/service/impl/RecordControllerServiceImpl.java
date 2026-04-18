package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.GPSDataService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.RecordControllerService;
import com.intellifleet.service.RecordService;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordControllerServiceImpl extends GenericAutowiredService implements RecordControllerService {

    @Autowired
    RecordService recordService;

    @Autowired
    GPSDataService gpsDataService;

    @Override
    public ApiResponseEntity get(Long formId, Long id) {
        return new ApiResponseEntity(ApiHttpStatus.OK, recordService.get(formId, id));
    }

    @Override
    public ApiResponseEntity getList(String reqParams) {
        return null;
    }

    @Override
    public ApiResponseEntity getListView(String reqParams) {
        return new ApiResponseEntity(ApiHttpStatus.OK, recordService.getListView(reqParams));
    }

    @Override
    public ApiResponseEntity saveOrUpdateRecord(SaveOrUpdateDTO saveOrUpdateDTO) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(recordService.saveOrUpdateRecord(saveOrUpdateDTO));
    }

    @Override
    public ApiResponseEntity getGpsData() {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, gpsDataService.getAllGPSData());
    }


}
