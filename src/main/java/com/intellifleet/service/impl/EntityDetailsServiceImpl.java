package com.intellifleet.service.impl;

import com.intellifleet.bean.EntityDetailsBean;
import com.intellifleet.service.EntityDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EntityDetailsServiceImpl implements EntityDetailsService {
    @Override
    public EntityDetailsBean getEntityDetailsBean(long entityId) {
        EntityDetailsBean entity = null;//sessionDetailsRepository.findById(sessionId).orElse(null);
        if(entity != null) {
            EntityDetailsBean bean = new EntityDetailsBean();
            BeanUtils.copyProperties(entity, bean);
            return bean;
        }
        return null;
    }
}
