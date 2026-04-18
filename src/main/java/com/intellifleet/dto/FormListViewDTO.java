package com.intellifleet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormListViewDTO {
    private Long id;
    private String name;
    private String label;
    private String tableName;
    private String note;
    private String activeRow;
    private LocalDateTime tmsCreate;
    private LocalDateTime tmsUpdate;
}
