package com.intellifleet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FormBuilderInfoDTO {
    private List<FormFieldGroupDTO> groups;

    public static FormBuilderInfoDTO getInstance(String reqParams) {
        FormBuilderInfoDTO reqDet = null;
        try {
            reqDet = new ObjectMapper().readValue(reqParams, FormBuilderInfoDTO.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reqDet;
    }
}
