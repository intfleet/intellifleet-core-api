package com.intellifleet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormConstraintDTO {
    private Long id;
    private String name;
    private String label;
    private String tableName;
    private String constraintType;
    private String constrainName;
    private String foreignColumn;
}
