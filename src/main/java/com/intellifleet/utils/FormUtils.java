package com.intellifleet.utils;

import com.intellifleet.constants.DataType;
import com.intellifleet.dto.FormFieldDTO;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class FormUtils {

    public Object parseValue(FormFieldDTO fieldDTO, Object value) {
        Object rtnValue = null;
        if(value != null) {
            rtnValue = switch (fieldDTO.getDataType()) {
                case DataType.STRING -> ParameterVerifier.getString(value);
                case DataType.INTEGER -> ParameterVerifier.getInteger(value);
                case DataType.FLOAT -> ParameterVerifier.getFloat(value);
                case DataType.DOUBLE -> ParameterVerifier.getDouble(value);
                case DataType.DATE -> DateUtils.parseDate(ParameterVerifier.getString(value));
                case DataType.TIME -> DateUtils.parseTime(ParameterVerifier.getString(value));
                case DataType.DATETIME -> DateUtils.parseDateTime(ParameterVerifier.getString(value));
                case DataType.BOOLEAN -> ParameterVerifier.isBoolean(value);
                default -> null;
            };
        }

        //Default value
        if(rtnValue == null) {
            rtnValue = switch (fieldDTO.getDataType()) {
                case DataType.STRING -> StringUtils.EMPTY;
                case DataType.INTEGER, DataType.FLOAT -> 0;
                case DataType.DOUBLE -> 0.0;
                case DataType.BOOLEAN -> false;
                default -> null;
            };
        }
        return rtnValue;
    }

    public String getPKFieldName(List<FormFieldDTO> fields) {
        Optional<FormFieldDTO> optional = fields.stream().filter(FormFieldDTO::isPk).findFirst();
        return optional.map(FormFieldDTO::getName).orElse(null);
    }
}
