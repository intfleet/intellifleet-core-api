package com.intellifleet.service;

import com.intellifleet.repository.GenericRepository;
import com.intellifleet.utils.JPAUtils;
import com.intellifleet.utils.MapStructUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericAutowiredService {

    @Autowired
    protected GenericRepository genericRepository;

    @Autowired
    protected MapStructUtils mapStructUtils;

    @Autowired
    protected JPAUtils jpaUtils;


}
