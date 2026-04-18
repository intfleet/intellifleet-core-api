package com.intellifleet.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SaveOrUpdateDTO {

    private Long formId;
    private Map<String, Object> fieldValues;
}
