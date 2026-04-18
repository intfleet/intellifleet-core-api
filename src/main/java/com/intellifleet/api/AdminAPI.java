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


@RequestMapping("/admin")
@Tag(name = "Greeting API", description = "Endpoints for AdminController")
public interface AdminAPI {

    @GetMapping(value = "/{object}/list/get")
    @Operation(
            summary = "Get object list",
            description = "Fetches a list of items for the given object type",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "404", description = "Object not found")
            }
    )
    @Parameters({
            @Parameter(name = "object", description = "The object type for which the list is requested", example = "users")
    })
    ResponseEntity<ApiResponseEntity> getList(@PathVariable("object") String object);

    @GetMapping(value = "/group-child/list/get/{customerClusterUid}")
    ResponseEntity<ApiResponseEntity> getClientGroupChildList(@PathVariable("customerClusterUid") Integer customerClusterUid);

    @GetMapping(value = "customer/list/get/{customerClusterUid}")
    ResponseEntity<ApiResponseEntity> getClientList(@PathVariable("customerClusterUid") Integer customerClusterUid);

}
