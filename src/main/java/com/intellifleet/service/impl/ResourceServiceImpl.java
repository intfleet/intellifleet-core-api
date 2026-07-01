package com.intellifleet.service.impl;


import com.intellifleet.bean.UserContext;
import com.intellifleet.constants.ApiHttpStatus;
import com.intellifleet.dto.*;
import com.intellifleet.entity.CoreMatrixView;
import com.intellifleet.entity.FormEntity;
import com.intellifleet.exceptions.NoRecordFoundException;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.RecordService;
import com.intellifleet.service.ResourceService;
import com.intellifleet.utils.FormUtils;
import com.intellifleet.utils.ParameterVerifier;
import com.intellifleet.utils.QueryUtils;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl extends GenericAutowiredService implements ResourceService {

    @Autowired
    protected FormService formService;


    @Override
    public List<ResourceDTO> getResourcesByCommodityId(Long commodityId) {
        List<CoreMatrixView> matrixViewList = genericRepository.findAll(CoreMatrixView.class, "id.commodityId = ?1", List.of(commodityId));
        return mapStructUtils.toResourceDTO(matrixViewList);
    }
}
