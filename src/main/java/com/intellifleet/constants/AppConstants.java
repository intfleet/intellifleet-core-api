package com.intellifleet.constants;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class AppConstants implements CommonAppConstants {

    public final String DB_SCHEMA_NAME_CORE = "coren";
    public final String VIEW_MVIEW_CORE_MATRIX = "mview_core_matrix";

//    public final String OBJECT_CUSTOMER = "customer";
//    public final String OBJECT_TRIP = "trip";

    //static block
    {
        STATUS_MESSAGE.put(RESP_STATUS_SUCCESS, RESP_STATUS_MSG_SUCCESS);
        STATUS_MESSAGE.put(RESP_STATUS_USER_CONTEXT_NOT_FOUND_EXCEPTION, RESP_STATUS_MSG_USER_CONTEXT_NOT_FOUND);

        STATUS_MESSAGE.put(RESP_STATUS_NO_RECORD_FOUND_EXCEPTION, RESP_STATUS_MSG_NO_RECORD_FOUND);
        STATUS_MESSAGE.put(RESP_STATUS_RECORD_ID_NOT_FOUND_EXCEPTION, RESP_STATUS_MSG_RECORD_ID_NOT_FOUND);
        STATUS_MESSAGE.put(RESP_STATUS_DUPLICATE_RECORD_EXCEPTION, RESP_STATUS_MSG_DUPLICATE_RECORD);
        STATUS_MESSAGE.put(RESP_STATUS_INVALID_CONFIRM_PASSWORD_EXCEPTION, RESP_STATUS_MSG_INVALID_CONFIRM_PASSWORD);
        STATUS_MESSAGE.put(RESP_STATUS_INVALID_CREDENTIALS_EXCEPTION, RESP_STATUS_MSG_INVALID_CREDENTIALS);
        STATUS_MESSAGE.put(RESP_STATUS_RECORD_CANCELED_EXCEPTION, RESP_STATUS_MSG_RECORD_CANCELED);
        STATUS_MESSAGE.put(RESP_STATUS_UNSUPPORTED_MEDIA_TYPE_EXCEPTION, RESP_STATUS_MSG_UNSUPPORTED_MEDIA_TYPE);
    }


    {
        //LINK_OBJECT_ENTITY_MAP.put(LINK_OBJECT_MAINTENANCE, LinkFlatDetailsAndUserDetailsEntity.class);
    }

    public <T> T createInstByTableName(String tableName) throws InstantiationException, IllegalAccessException {
        if(tableName != null) {
            tableName = tableName.toLowerCase();
            Class<?> clss = LINK_OBJECT_ENTITY_MAP.get(tableName);
            return clss != null ? (T) clss.newInstance() : null;
        }
        return null;
    }
}
