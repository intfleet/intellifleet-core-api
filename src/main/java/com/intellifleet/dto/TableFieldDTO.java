package com.intellifleet.dto;

import com.intellifleet.constants.DataType;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TableFieldDTO {

    private Long id;
    private String name;
    private DataType dataType;
    private Integer size;
    private Boolean nullable;
    private String constraintType;
    private String constrainName;
    private String foreignTable;
    private String foreignColumn;
}
