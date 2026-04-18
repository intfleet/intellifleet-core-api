package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMasterDTO {

    @JsonProperty("id")
    private Long rowWid;

    @JsonProperty("customerID")
    private Long customerUid;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("managerCustomerID")
    private Long managerCustomerId;

    @JsonProperty("customerClusterID")
    private Long customerClusterId;

    @JsonProperty("relationType")
    private String relationType;

    @JsonProperty("customerAuthentication")
    private String customerAuthentication;

    private LocalDateTime tmsCreate;
    private LocalDateTime tmsUpdate;
    private String activeRow;
    private String note;


}

