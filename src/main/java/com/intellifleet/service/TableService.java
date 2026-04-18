package com.intellifleet.service;

import com.intellifleet.dto.TableFieldDTO;
import com.intellifleet.entity.FormFieldEntity;

import java.util.List;

public interface TableService {
    List<FormFieldEntity> getTableFieldEntityList(String tableName);

    List<TableFieldDTO> getTableFields(String tableName);

    List<FormFieldEntity> getTableForeignKeyList(String tableName);
}
