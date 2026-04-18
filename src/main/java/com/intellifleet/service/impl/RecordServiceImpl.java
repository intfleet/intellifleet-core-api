package com.intellifleet.service.impl;


import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.*;
import com.intellifleet.entity.FormEntity;
import com.intellifleet.exceptions.NoRecordFoundException;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.RecordService;
import com.intellifleet.utils.FormUtils;
import com.intellifleet.utils.ParameterVerifier;
import com.intellifleet.utils.QueryUtils;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl extends GenericAutowiredService implements RecordService {

    @Autowired
    protected FormService formService;


    @Override
    public Map<String, Object> get(Long formId, Long id) {
        FormDTO form = formService.get(formId);
        List<FormFieldDTO> formFields = formService.getFormFields(form.getId());
        String pkFieldName = FormUtils.getPKFieldName(formFields);
        Map<String, Object> clause = Map.of(pkFieldName, id);
        String query = QueryUtils.createSelectQuery(form.getTableName(), formFields, clause);
        List<Object[]> rowsList = genericRepository.findByNativeQuery(query, clause);
        if(rowsList.isEmpty()) {
            throw new NoRecordFoundException();
        }
        List<Map<String, Object>> records = mapFieldValue(formFields, rowsList);
        return records.getFirst();


//        Class<?> entityClass = jpaUtils.findEntityByTableName(form.getTableName());
//        Class<?> dtoClass = jpaUtils.findListViewDTOByEntity(entityClass);
//        Object obj = genericRepository.findById(entityClass, id);
//        return Utils.copyProperties(dtoClass, obj);
    }

    @Override
    public List<?> getList(String reqParams) {
        return null;
    }

    @Override
    public Map<String, Object> getListView(String reqParams) {
        ListViewRequestParamDTO requestParamDTO = ListViewRequestParamDTO.getInstance(reqParams);

        FormDTO form = formService.get(requestParamDTO.getFormId());
//        Class<?> entityClass = jpaUtils.findEntityByTableName(form.getTableName());
//        Class<?> dtoClass = jpaUtils.findListViewDTOByEntity(entityClass);

        List<FormFieldDTO> formFields = formService.getFormFields(form.getId());

        String query = QueryUtils.createSelectQuery(form.getTableName(), formFields);
        List<Object[]> rowsList = genericRepository.findByNativeQuery(query, Map.of());
        List<Map<String, Object>> records = mapFieldValue(formFields, rowsList);

        Map<String, Object> rtnResult = new HashMap<>();
        rtnResult.put("fields", formFields);
        rtnResult.put("records", records);

        return rtnResult;
    }

    public List<Map<String, Object>> mapFieldValue(List<FormFieldDTO> formFields, List<Object[]> rowsList) {
        List<Map<String, Object>> rtnList = new ArrayList<>();
        Map<String, Object> rtnCol;

        int colPos = 0;
        for(Object[] columns : rowsList) {
            colPos = 0;
            rtnCol = new HashMap<>();

            for(FormFieldDTO field : formFields) {
                rtnCol.put(field.getName(), columns[colPos++]);
            }
            rtnList.add(rtnCol);
        }
        return rtnList;
    }

    @Override
    public ApiHttpStatus saveOrUpdateRecord(SaveOrUpdateDTO saveOrUpdateDTO) {
        UserContext userContext = Utils.getUserContext();
        FormEntity form = formService.getFormEntity(saveOrUpdateDTO.getFormId());
        String pkFieldName = FormUtils.getPKFieldName(formService.getFormFields(saveOrUpdateDTO.getFormId()));

        parseFormValues(saveOrUpdateDTO);

        String tableName = "admin."+form.getTableName();

        ApiHttpStatus status;
        boolean isSuccess = false;
        long id = ParameterVerifier.getLong(saveOrUpdateDTO.getFieldValues().getOrDefault(pkFieldName, 0));
        if(id > 0) {
            isSuccess = genericRepository.update(tableName, saveOrUpdateDTO, pkFieldName);
            status = isSuccess ? ApiHttpStatus.OK : ApiHttpStatus.UNPROCESSABLE_ENTITY;
        } else {
            isSuccess = genericRepository.saveRecord(tableName, saveOrUpdateDTO);
            status = isSuccess ? ApiHttpStatus.CREATED : ApiHttpStatus.UNPROCESSABLE_ENTITY;
        }

        return status;
    }

    public void parseFormValues(SaveOrUpdateDTO saveOrUpdateDTO) {
        FormEntity formEntity = formService.getFormEntity(saveOrUpdateDTO.getFormId());
        if(formEntity == null) {
            throw new NoRecordFoundException();
        }
        FormBuilderInfoDTO formDTO = formService.getFormBuilderInfoDTO(formEntity.getId());
        List<FormFieldDTO> fieldDTOList = formDTO.getGroups().stream()
                .map(FormFieldGroupDTO::getFields).flatMap(List::stream)
                .toList();
        for(FormFieldDTO field : fieldDTOList) {
            if(saveOrUpdateDTO.getFieldValues().containsKey(field.getName())) {
                Object value = saveOrUpdateDTO.getFieldValues().get(field.getName());
                value = FormUtils.parseValue(field, value);
                saveOrUpdateDTO.getFieldValues().put(field.getName(), value);
            }
        }
    }
}
