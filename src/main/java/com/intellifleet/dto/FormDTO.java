package com.intellifleet.dto;

import lombok.Data;

@Data
public class FormDTO {

    private Long id;
    private String name;
    private String label;
    private String tableName;
    private String note;
}
