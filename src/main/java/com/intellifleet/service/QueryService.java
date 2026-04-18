package com.intellifleet.service;

import com.intellifleet.dto.DropDownOptionDTO;
import com.intellifleet.dto.QueryDTO;

import java.util.List;
import java.util.Map;

public interface QueryService {
    List<QueryDTO> getQueries();

    List<DropDownOptionDTO> getFormDown(Long id);

    Map<String, Object> getQueryBuilderInfo(Long formId);

    List<String> saveOrUpdate(QueryDTO queryDTO);
}
