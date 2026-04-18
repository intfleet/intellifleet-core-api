package com.intellifleet.service.impl;

import com.intellifleet.dto.TableFieldDTO;
import com.intellifleet.entity.FormFieldEntity;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TableServiceImpl extends GenericAutowiredService implements TableService {

    @Override
    public List<FormFieldEntity> getTableFieldEntityList(String tableName) {
        String sql1 = "SELECT " +
                " row_number() OVER () AS id," +
                " table_schema," +
                " table_name," +
                " column_name," +
                " data_type," +
                " character_maximum_length," +
                " is_nullable" +
                " FROM information_schema.columns" +
                " WHERE table_schema = 'admin'" +
                " AND table_name = ?1";
        String sql ="SELECT" +
                "    row_number() OVER () AS id," +
                "    c.table_schema," +
                "    c.table_name," +
                "    c.column_name," +
                "    c.data_type," +
                "    c.character_maximum_length," +
                "    c.is_nullable," +
                "    tc.constraint_type," +
                "    kcu.constraint_name," +
                "    ccu.table_name  AS foreign_table," +
                "    ccu.column_name AS foreign_column" +
                " FROM information_schema.columns c" +
                " LEFT JOIN information_schema.key_column_usage kcu" +
                "       ON c.table_schema = kcu.table_schema" +
                "      AND c.table_name = kcu.table_name" +
                "      AND c.column_name = kcu.column_name" +
                " LEFT JOIN information_schema.table_constraints tc" +
                "       ON kcu.constraint_name = tc.constraint_name" +
                "      AND kcu.table_schema = tc.table_schema" +
                //"      AND tc.constraint_type = 'FOREIGN KEY'" +
                " LEFT JOIN information_schema.constraint_column_usage ccu" +
                "       ON ccu.constraint_name = kcu.constraint_name" +
                "      AND ccu.table_schema = kcu.table_schema" +
                " WHERE c.table_name = ?1" +
                " ORDER BY c.ordinal_position";
        return genericRepository.findByNativeQuery(FormFieldEntity.class, sql, List.of(tableName));
    }

    @Override
    public List<TableFieldDTO> getTableFields(String tableName) {
        return getTableFieldEntityList(tableName)
                .stream()
                .map(mapStructUtils::toFormFieldDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormFieldEntity> getTableForeignKeyList(String tableName) {
        List<FormFieldEntity> fields = getTableFieldEntityList(tableName);
        return fields.stream().filter(f -> "FOREIGN KEY".equalsIgnoreCase(f.getConstraintType())).toList();
    }
}
