package com.intellifleet.service;

import com.intellifleet.dto.ResourceDTO;

import java.util.List;

public interface ResourceService {
    List<ResourceDTO> getResourcesByCommodityId(Long commodityId);
}
