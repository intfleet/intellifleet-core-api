package com.intellifleet.service;

import com.intellifleet.bean.EntityDetailsBean;

public interface EntityDetailsService {
    EntityDetailsBean getEntityDetailsBean(long entityId);
}
