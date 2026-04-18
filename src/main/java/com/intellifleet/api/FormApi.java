package com.intellifleet.api;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.FormBuilderInfoDTO;
import com.intellifleet.dto.FormDTO;
import com.intellifleet.dto.SaveOrUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/form")
@Tag(name = "Forms API", description = "Endpoints for FormsController")
public interface FormApi {

    @PostMapping
    ResponseEntity<ApiResponseEntity> saveOrUpdateForm(@RequestBody FormDTO formDTO);

    @GetMapping(value = "/{id}")
    ResponseEntity<ApiResponseEntity> get(@PathVariable("id") Long id);

    @GetMapping(value = "/listview")
    ResponseEntity<ApiResponseEntity> listview(@RequestParam("params") String params);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ApiResponseEntity> delete(@PathVariable("id") Long id);

    @PostMapping(value = "/{id}/form-builder-info")
    ResponseEntity<ApiResponseEntity> saveOrUpdateFormBuilderInfo(@PathVariable("id") Long id, @RequestBody FormBuilderInfoDTO formBuilderInfoDTO);

    @GetMapping(value = "/{id}/form-builder-info")
    ResponseEntity<ApiResponseEntity> getFormBuilderInfo(@PathVariable("id") Long id);

    @GetMapping(value = "/{id}/form-info")
    ResponseEntity<ApiResponseEntity> getFormInfo(@PathVariable("id") Long id);

    @GetMapping(value = "/{tableName}/fields")
    ResponseEntity<ApiResponseEntity> getTableFields(@PathVariable("tableName") String tableName);


}
