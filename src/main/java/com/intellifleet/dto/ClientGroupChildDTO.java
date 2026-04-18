package com.intellifleet.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientGroupChildDTO {
    @JsonProperty("id")
    private Integer rowWid;
    private Integer customerClusterID;
    private String dbCode;
    private String databaseName;
    private String databaseType;
    private Integer port;
    private String host;
    private String user;
    private String password;
    private String dbLink;
    private String owner;
    private LocalDateTime tmsCreate;
    private LocalDateTime tmsUpdate;
    private String activeRow;
    private String note;
}
