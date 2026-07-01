package com.intellifleet.api;

import com.intellifleet.dto.ApiResponse;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resource")
@Tag(name = "Resource API", description = "Endpoints for ResourceController")
public interface ResourceAPI {

    @Operation(summary = "Get Resources by Commodity Id")
    @GetMapping(value = "/list/{commodityId}")
    ResponseEntity<ApiResponse.ApiResult<Object>> getResourcesByCommodityId(@PathVariable("commodityId") Long commodityId);
}
