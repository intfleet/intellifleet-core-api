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
public class ClientGroupDTO {

    @JsonProperty("id")
    private Long rowWid;
    private Long customerClusterID;
    private String customerClusterName;
    private LocalDateTime tmsCreate;
    private LocalDateTime tmsUpdate;
    private String activeRow;
    private String note;
}

