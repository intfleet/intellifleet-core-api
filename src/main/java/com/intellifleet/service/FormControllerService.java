package com.intellifleet.service;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.FormBuilderInfoDTO;
import com.intellifleet.dto.FormDTO;
import com.intellifleet.dto.SaveOrUpdateDTO;

public interface FormControllerService {
    ApiResponseEntity saveOrUpdateForm(FormDTO formDTO);
    ApiResponseEntity get(Long id);
    ApiResponseEntity listview(String params);
    ApiResponseEntity delete(Long id);

    ApiResponseEntity getFormBuilderInfo(Long id);
    ApiResponseEntity saveOrUpdateFormBuilderInfo(Long id, FormBuilderInfoDTO formBuilderInfoDTO);

    ApiResponseEntity getFormInfo(Long id);

    ApiResponseEntity getTableFields(String tableName);
}
