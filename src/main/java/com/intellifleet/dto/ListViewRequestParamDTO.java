package com.intellifleet.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class ListViewRequestParamDTO {
    private Long formId;

    public static ListViewRequestParamDTO getInstance(String reqParams) {
        ListViewRequestParamDTO reqDto = null;
        try {
            reqDto = new ObjectMapper().readValue(reqParams, ListViewRequestParamDTO.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reqDto;
    }
}
