package com.intellifleet.controller;


import com.intellifleet.api.FormApi;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.FormBuilderInfoDTO;
import com.intellifleet.dto.FormDTO;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.FormControllerService;
import com.intellifleet.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FormController implements FormApi {

    @Autowired
    FormControllerService formControllerService;

    @Override
    public ResponseEntity<ApiResponseEntity> saveOrUpdateForm(FormDTO formDTO) {
        ApiResponseEntity responseEntity = formControllerService.saveOrUpdateForm(formDTO);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> get(Long id) {
        ApiResponseEntity responseEntity = formControllerService.get(id);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> listview(String params) {
        ApiResponseEntity responseEntity = formControllerService.listview(params);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> delete(Long id) {
        ApiResponseEntity responseEntity = formControllerService.delete(id);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getFormBuilderInfo(Long id) {
        ApiResponseEntity responseEntity = formControllerService.getFormBuilderInfo(id);
        return ResponseEntity.status(responseEntity.getStatus()).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> saveOrUpdateFormBuilderInfo(Long id, FormBuilderInfoDTO formBuilderInfoDTO) {
        ApiResponseEntity responseEntity = formControllerService.saveOrUpdateFormBuilderInfo(id, formBuilderInfoDTO);
        return ResponseEntity.status(responseEntity.getStatus()).build();
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getFormInfo(Long id) {
        ApiResponseEntity responseEntity = formControllerService.getFormInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getTableFields(String tableName) {
        ApiResponseEntity responseEntity = formControllerService.getTableFields(tableName);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }
}
