package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellifleet.constants.DataType;
import lombok.Data;

@Data
public class FormFieldDTO {
    private String id;
    private String name;
    private String foreignKey;
    private String label;
    private String fieldType;
    private DataType dataType;
    private QueryDTO query;

    @JsonProperty("isPK")
    private boolean pk;

    @JsonProperty("isHidden")
    private boolean hidden;

    @JsonProperty("isSystemField")
    private boolean systemField;

    @JsonProperty("isRequired")
    private boolean required;
}
