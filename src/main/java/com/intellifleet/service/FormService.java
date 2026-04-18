package com.intellifleet.service;

import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.*;
import com.intellifleet.entity.FormEntity;

import java.util.List;
import java.util.Map;

public interface FormService {

    FormDTO saveOrUpdateForm(FormDTO formDTO);
    FormDTO get(Long id);
    List<FormListViewDTO> listview(String params);
    void delete(Long id);

    Map<String, Object> getFormBuilderInfo(Long id);
    FormBuilderInfoDTO saveOrUpdateFormBuilderInfo(Long id, FormBuilderInfoDTO formBuilderInfoDTO);

    FormBuilderInfoDTO getFormInfo(Long id);

    List<FormFieldDTO> getFormFields(Long formId);

    FormEntity getFormEntity(String object);

    FormEntity getFormEntity(Long id);

    FormBuilderInfoDTO getFormBuilderInfoDTO(Long id);

    String getTableName(Long formId);

    List<FormEntity> getFormListByTableNames(List<String> tableNameList);
}
