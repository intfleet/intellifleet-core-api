package com.intellifleet.service.impl;

import com.intellifleet.dto.*;
import com.intellifleet.entity.FormEntity;
import com.intellifleet.entity.FormFieldEntity;
import com.intellifleet.entity.QueryEntity;
import com.intellifleet.exceptions.NoRecordFoundException;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.QueryService;
import com.intellifleet.service.TableService;
import com.intellifleet.utils.FileUtils;
import com.intellifleet.utils.ParameterVerifier;
import com.intellifleet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl extends GenericAutowiredService implements QueryService {

    @Autowired
    FormService formService;

    @Autowired
    TableService tableService;

    @Override
    public List<QueryDTO> getQueries() {
        List<QueryEntity> queryEntityList = genericRepository.findAll(QueryEntity.class);

        return queryEntityList.stream().map(mapStructUtils::toQueryDTO).toList();
    }

    @Override
    public List<DropDownOptionDTO> getFormDown(Long id) {
        QueryEntity queryEntity = genericRepository.findById(QueryEntity.class, id);
        if(queryEntity == null) {
            throw new NoRecordFoundException();
        }

        if("CONSTANTS".equalsIgnoreCase(queryEntity.getType())) {
            Path path = FileUtils.getFilePathByType("query");
            if(path != null) {
                String fileName = queryEntity.getName() + ".json";
                path = Paths.get(path.toString(), fileName);
                String content = FileUtils.readFile(path);
                return Utils.parseArrayJSON(content, DropDownOptionDTO.class);
            }
        } else if("QUERY".equalsIgnoreCase(queryEntity.getType())) {
            Path path = FileUtils.getFilePathByType("query");
            if(path != null) {
                String fileName = queryEntity.getName() + ".sql";
                path = Paths.get(path.toString(), fileName);
                String sql = FileUtils.readFile(path);

                return genericRepository.findByNativeQuery(sql)
                        .stream()
                        .map(arr -> new DropDownOptionDTO(
                                ParameterVerifier.getLong(arr[0]),
                                ParameterVerifier.getString(arr[1]),
                                ParameterVerifier.getString(arr[2])))
                        .toList();
            }
        }

        return List.of();
    }

    @Override
    public Map<String, Object> getQueryBuilderInfo(Long formId) {
        Map<String, Object> rtnResult = new HashMap<>();


        FormEntity formEntity = formService.getFormEntity(formId);
        if(formEntity != null) {
            List<FormFieldEntity> tableForeignKeyList = tableService.getTableForeignKeyList(formEntity.getTableName());
            List<String> tableList = tableForeignKeyList
                    .stream()
                    .map(FormFieldEntity::getForeignTable)
                    .toList();
            List<FormEntity> foreignTableFormList = formService.getFormListByTableNames(tableList);
//            foreignTableFormList.addFirst(formEntity);

            List<FormConstraintDTO> formList = foreignTableFormList.stream().map(f ->{
                        Optional<FormFieldEntity> first = tableForeignKeyList.stream().filter(m -> f.getTableName().equals(m.getForeignTable())).findFirst();
                        return FormConstraintDTO.builder()
                                .id(f.getId())
                                .name(f.getName())
                                .label(f.getLabel())
                                .tableName(f.getTableName())
                                .foreignColumn(first.isPresent() ? first.get().getForeignColumn() : "")
                                .build();

                    })
                    .toList();


            List<FormFieldDTO> formFields = formService.getFormFields(formId);
            formFields.forEach( f -> {
                f.setName(formEntity.getTableName()+"."+f.getName());
            });
            rtnResult.put( "formList", formList);
            rtnResult.put( "fields", formFields);

        }
        return rtnResult;
    }

    @Override
    public List<String> saveOrUpdate(QueryDTO queryDTO) {

        return List.of();
    }

}
