package com.intellifleet.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DropDownOptionDTO {

    private Long id;
    private String value;
    private String text;

    public DropDownOptionDTO(Long id, String value, String text) {
        this.id = id;
        this.value = value;
        this.text = text;
    }
}
