package com.intellifleet.api;

import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/record")
@Tag(name = "Records API", description = "Endpoints for RecordsController")
public interface RecordAPI {

    @GetMapping(value = "/{formId}/{id}")
    @Operation(
            summary = "Get object list",
            description = "Fetches a list of items for the given object type",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "404", description = "Object not found")
            }
    )
    @Parameters({
            @Parameter(name = "object", description = "The object type for which the list is requested", example = "users"),
            @Parameter(name = "id", description = "Record Identifier", example = "users")
    })
    ResponseEntity<ApiResponseEntity> get(@PathVariable("formId") Long formId, @PathVariable("id") Long id);

    @GetMapping(value = "/list")
    @Operation(
            summary = "Get object list",
            description = "Fetches a list of items for the given object type",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "404", description = "Object not found")
            }
    )
    ResponseEntity<ApiResponseEntity> getList(@RequestParam("params") String reqParams);

    @GetMapping(value = "/listview")
    @Operation(
            summary = "Get object list",
            description = "Fetches a list of items for the given object type",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "404", description = "Object not found")
            }
    )
    ResponseEntity<ApiResponseEntity> getListView(@RequestParam("params") String reqParams);


    @PostMapping
    ResponseEntity<ApiResponseEntity> saveOrUpdateRecord(@RequestBody SaveOrUpdateDTO saveOrUpdateDTO);

    @Operation(summary = "Get GPS data")
    @GetMapping(value = "/gps-data")
    ResponseEntity<ApiResponseEntity> getGpsData();
}
