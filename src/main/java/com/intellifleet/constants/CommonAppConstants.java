package com.intellifleet.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommonAppConstants {
	
	public static final String STRING_TIMEZONE_ID_IST = "IST";
	public static final String STRING_TIMEZONE_ID_UTC = "UTC";
	
	public static final int RESP_STATUS_SUCCESS = 100;	
	public static final int RESP_STATUS_FAIL = 0;
	public static final int RESP_STATUS_EXCEPTION = 200;	
	public static final int RESP_STATUS_USER_DISABLED_EXCEPTION = 201;
	public static final int RESP_STATUS_INVALID_CREDENTIALS_EXCEPTION = 202;
	public static final int RESP_STATUS_USER_CONTEXT_NOT_FOUND_EXCEPTION = 203;
	public static final int RESP_STATUS_INVALID_CONFIRM_PASSWORD_EXCEPTION = 204;
	public static final int RESP_STATUS_MAX_FILE_UPLOAD_SIZE_EXCEEDED_EXCEPTION = 205;
	
	public static final int RESP_STATUS_NO_RECORD_FOUND_EXCEPTION = 220;
	public static final int RESP_STATUS_RECORD_ID_NOT_FOUND_EXCEPTION = 221;
	public static final int RESP_STATUS_DUPLICATE_RECORD_EXCEPTION = 222;
	public static final int RESP_STATUS_INVALID_EMAIL_EXCEPTION = 223;
	public static final int RESP_STATUS_RECORD_CANCELED_EXCEPTION = 224;
	
	public static final int RESP_STATUS_UNSUPPORTED_MEDIA_TYPE_EXCEPTION = 230;
	
	public static final String RESP_STATUS_MSG_SUCCESS = "SUCCESS";
	public static final String RESP_STATUS_MSG_NO_RECORD_FOUND = "Record not found";
	public static final String RESP_STATUS_MSG_RECORD_ID_NOT_FOUND = "Record id not found";
	public static final String RESP_STATUS_MSG_DUPLICATE_RECORD = "Duplicate Record found";
	public static final String RESP_STATUS_MSG_USER_CONTEXT_NOT_FOUND = "User Context not found";
	public static final String RESP_STATUS_MSG_INVALID_CONFIRM_PASSWORD = "New password and confirm password should be same.";
	public static final String RESP_STATUS_MSG_INVALID_CREDENTIALS = "Invalid credentials.";
	public static final String RESP_STATUS_MSG_RECORD_CANCELED = "Record canceled";
	public static final String RESP_STATUS_MSG_UNSUPPORTED_MEDIA_TYPE = "Unsupported media type";
	
	public static final Map<Integer, String> STATUS_MESSAGE = new HashMap<>();
	static {
		
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
	

	public static final String LINK_OBJECT_MAINTENANCE = "link_flat_details_user_details";
	
	public static final Map<String, Class> LINK_OBJECT_ENTITY_MAP = new HashMap<>();
	static {
		//LINK_OBJECT_ENTITY_MAP.put(LINK_OBJECT_MAINTENANCE, LinkFlatDetailsAndUserDetailsEntity.class);
	}
	
	
	public static <T> T createInstByTableName(String tableName) throws InstantiationException, IllegalAccessException {
		if(tableName != null) {
			tableName = tableName.toLowerCase();
			Class<?> clss = CommonAppConstants.LINK_OBJECT_ENTITY_MAP.get(tableName);
			return clss != null ? (T) clss.newInstance() : null;
		}
		return null;		
	}
	
	public static final long INVALID_RECORD_ID = -9999;
	public static final long SYSTEM_USER_ID = -99999;
	public static final long LIST_OPTION_SELECT_VAL = -99999;
	
	public static final String SYSTEM_USER_NAME = "System";
	
	public static String getSystemUserName(long id) {
		return id == SYSTEM_USER_ID ? SYSTEM_USER_NAME : "";
	}
	
	
	public static final String SADMIN_USER_NAME = "Super Admin";
	
	public static final String SADMIN_CONTACT_NO = "1111111111";
	public static final String USER_DEFAULT_PASSWORD = "pass";
	
	public static final String USER_PERMISSION_CREATE = "CREATE";
	public static final String USER_PERMISSION_VIEW = "VIEW";
	public static final String USER_PERMISSION_EDIT = "EDIT";
	public static final String USER_PERMISSION_DELETE = "DELETE";
	
	public static final String REQ_HEADER_AUTHORIZATION = "Authorization";
	public static final String REQ_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String REQ_HEADER_ACCEPT = "Accept";
	public static final String REQ_HEADER_X_REQUESTED_WITH = "X-Requested-With";
	public static final String REQ_HEADER_IS_ENTITY_LIST_REQUIRED = "is-entity-list-required";
	public static final String REQ_HEADER_ENTITY_ID = "entity-id";
	
	public static String getAccessControlAllowHeaders() {
		return String.join(",", REQ_HEADER_AUTHORIZATION,
                REQ_HEADER_CONTENT_TYPE,
                REQ_HEADER_ACCEPT,
                REQ_HEADER_X_REQUESTED_WITH,
                REQ_HEADER_IS_ENTITY_LIST_REQUIRED,
                REQ_HEADER_ENTITY_ID);
	}
	
	public static Map<Long, String> USER_NAME_BY_ID = new HashMap<>();
}
