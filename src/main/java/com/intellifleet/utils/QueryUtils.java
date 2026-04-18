package com.intellifleet.utils;

import com.intellifleet.dto.FormFieldDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class QueryUtils {

    public String createNativeQuery(String tableName, Map<String, Object> fields) {
        String template = "INSERT INTO %s (%s) VALUES(%s)";

        List<String> fieldList = fields.keySet().stream().map(String::trim).toList();
        return String.format(
                template,
                tableName,
                String.join(", ", fieldList),
                fieldList.stream().map(m -> ":"+m).collect(Collectors.joining(","))
        );
    }

    public String createUpdateNativeQuery(String tableName, Map<String, Object> fields, String idColumn) {
        String template = "UPDATE %s SET %s WHERE %s = :%s";

        // Build "col1 = :col1, col2 = :col2"
        String setClause = fields.keySet().stream()
                .map(key -> key.trim() + " = :" + key.trim())
                .collect(Collectors.joining(", "));

        return String.format(template, tableName, setClause, idColumn, idColumn);
    }

    public String createSelectQuery(String tableName, List<FormFieldDTO> fields) {
        return createSelectQuery(tableName, fields, null, null);
    }

    public String createSelectQuery(String tableName, List<FormFieldDTO> fields, Map<String, Object> requiredClause) {
        return createSelectQuery(tableName, fields, requiredClause, null);
    }

    public String createSelectQuery(String tableName, List<FormFieldDTO> fields, String whereClause) {
        return createSelectQuery(tableName, fields, null, whereClause);
    }

    public String createSelectQuery(String tableName, List<FormFieldDTO> fields, Map<String, Object> requiredClause, String whereClause) {
        log.info("Started executing createSelectQuery()");
        if(fields == null || fields.isEmpty()) throw new RuntimeException("Fields not found");
        String schema = "admin";
        String selectFields = fields.stream().map(FormFieldDTO::getName).collect(Collectors.joining(", "));

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");

        //Select fields
        sb.append(" ").append(selectFields).append(" ");
        //Table names
        sb.append(" FROM ").append(schema).append(".").append(tableName).append(" ");

        if(requiredClause != null) {
            String reqClause = requiredClause.keySet().stream().map(f -> f.trim() + " = :"+f.trim()).collect(Collectors.joining(" AND "));
            sb.append(" WHERE ").append(reqClause).append(" ");
        }

        if(StringUtils.isNotBlank(whereClause)) {
            if(whereClause.contains("WHERE")) {
                sb.append(" AND ").append(whereClause).append(" ");
            } else {
                sb.append(" WHERE ").append(whereClause).append(" ");
            }
        }
        log.info("Native Query: {}", sb);
        return sb.toString();
    }
}
