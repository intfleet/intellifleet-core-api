package com.intellifleet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormFieldGroupDTO {
    private String id;
    private String label;
    private String fieldType;
    private Integer columnSize;
    private List<FormFieldDTO> fields;
}
