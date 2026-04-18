package com.intellifleet.utils;

import com.intellifleet.dto.*;
import com.intellifleet.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructUtils {

    //@Mapping(target = "id", ignore = true)
//    @Mapping(target = "customerUid", ignore = true)
    ClientGroupDTO toClientGroupDTO(ClientGroupEntity entity);
    ClientGroupChildDTO toClientGroupChildDTO(ClientGroupChildEntity entity);

    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "customerUid", ignore = true)
    CustomerMasterDTO toCustomerMasterDTO(CustomerMasterEntity entity);

    @Mapping(target = "name", source = "columnName")
    @Mapping(target = "dataType", expression = "java(com.intellifleet.constants.DBDataTypeMap.getJsonType(entity.getDataType()))")
    @Mapping(target = "size", source = "characterMaximumLength")
    @Mapping(target = "nullable", expression = "java( \"YES\".equals(entity.getNullable()) )")
    TableFieldDTO toFormFieldDTO(FormFieldEntity entity);

    FormDTO toFormDTO(FormEntity entity);
    FormEntity toFormEntity(FormDTO form);
    FormListViewDTO toFormListViewDTO(FormEntity entity);

    QueryDTO toQueryDTO(QueryEntity entity);
}
