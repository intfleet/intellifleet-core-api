package com.intellifleet.controller;


import com.intellifleet.api.AdminAPI;
import com.intellifleet.constants.ApiConstants;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.ApiResponseEntity;
import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AdminController implements AdminAPI {

    @Autowired
    AdminService adminService;

    @Override
    public ResponseEntity<ApiResponseEntity> getList(@PathVariable("object") String object) {
        log.info("Started executing API getGroupList");
        ApiResponseEntity resp = new ApiResponseEntity(ApiHttpStatus.INTERNAL_SERVER_ERROR);
        if("group".equalsIgnoreCase(object)) {
            resp = adminService.getGroupList();
        } else if("group-child".equalsIgnoreCase(object)) {
            resp = adminService.getGroupList();
        }
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getClientGroupChildList(@PathVariable("customerClusterUid") Integer customerClusterUid) {
        log.info("Started executing API getClientGroupChildList");
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getClientGroupChildList(customerClusterUid));
    }

    @Override
    public ResponseEntity<ApiResponseEntity> getClientList(@PathVariable("customerClusterUid") Integer customerClusterUid) {
        log.info("Started executing API getGroupClientList");
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getClientList(customerClusterUid));
    }
}
