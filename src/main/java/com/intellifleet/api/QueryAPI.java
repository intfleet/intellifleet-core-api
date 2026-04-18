package com.intellifleet.api;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.QueryDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/query")
@Tag(name = "Query API", description = "Endpoints for QueryController")
public interface QueryAPI {

    @PostMapping
    ResponseEntity<ApiResponseEntity> save(@RequestBody QueryDTO queryDTO);

    @GetMapping(value = "/{id}/form-dropdown")
    ResponseEntity<ApiResponseEntity> getFormDown(@PathVariable("id") Long id);

    @GetMapping(value = "/builder-info/{formId}")
    ResponseEntity<ApiResponseEntity> getQueryBuilderInfo(@PathVariable("formId") Long formId);
}
