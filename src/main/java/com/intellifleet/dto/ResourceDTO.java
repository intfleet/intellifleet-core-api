package com.intellifleet.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String instrumentId;
    private Long commodityId;
    private Long customerUid;

    private String resources;
    private String resourcesAlias;
    private String resourcesName;
    private String incident;
}
