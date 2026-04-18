package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.FormBuilderInfoDTO;
import com.intellifleet.dto.FormDTO;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.entity.FormEntity;
import com.intellifleet.service.FormControllerService;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.TableService;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormControllerServiceImpl extends GenericAutowiredService implements FormControllerService {

    @Autowired
    FormService formService;

    @Autowired
    TableService tableService;

    @Override
    public ApiResponseEntity saveOrUpdateForm(FormDTO formDTO) {
        UserContext userContext = Utils.getUserContext();

        ApiHttpStatus status = ApiHttpStatus.OK;
        if(formDTO.getId() == null || formDTO.getId() <= 0) {
            status = ApiHttpStatus.CREATED;
        }
        return new ApiResponseEntity(status, formService.saveOrUpdateForm(formDTO));
    }

    @Override
    public ApiResponseEntity get(Long id) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, formService.get(id));
    }

    @Override
    public ApiResponseEntity listview(String params) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, formService.listview(params));
    }

    @Override
    public ApiResponseEntity delete(Long id) {
        UserContext userContext = Utils.getUserContext();

        formService.delete(id);
        return new ApiResponseEntity(ApiHttpStatus.NO_CONTENT);
    }

    @Override
    public ApiResponseEntity getFormBuilderInfo(Long id) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, formService.getFormBuilderInfo(id));
    }

    @Override
    public ApiResponseEntity saveOrUpdateFormBuilderInfo(Long id, FormBuilderInfoDTO formBuilderInfoDTO) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, formService.saveOrUpdateFormBuilderInfo(id, formBuilderInfoDTO));
    }

    @Override
    public ApiResponseEntity getFormInfo(Long id) {
        UserContext userContext = Utils.getUserContext();

        return new ApiResponseEntity(ApiHttpStatus.OK, formService.getFormInfo(id));
    }

    @Override
    public ApiResponseEntity getTableFields(String tableName) {
        UserContext userContext = Utils.getUserContext();
        return new ApiResponseEntity(ApiHttpStatus.OK, tableService.getTableFields(tableName));
    }
}
