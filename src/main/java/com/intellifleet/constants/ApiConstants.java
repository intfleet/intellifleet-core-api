package com.intellifleet.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ApiConstants implements CommonAppConstants {
	
	public static final String STRING_TIMEZONE_ID_IST = "IST";
	public static final String STRING_TIMEZONE_ID_UTC = "UTC";
		
	/*public static Long getSAdminUserId(UserDetailsEntity userDetailsEntity) {
		if(userDetailsEntity != null && USER_ROLE_SADMIN.equals(userDetailsEntity.getRole())) {
			return userDetailsEntity.getId();
		} else {
			throw new RuntimeException("Invalid SAdmin.");
		}
	}*/
	
//	public static String getSAdminUserName(UserDetailsEntity userDetailsEntity) {
//		if(userDetailsEntity != null && USER_ROLE_SADMIN.equals(userDetailsEntity.getRole())) {
//			return userDetailsEntity.getFirstName();
//		} else {
//			throw new RuntimeException("Invalid SAdmin.");
//		}
//	}
	
	public static final List<String> DEFAULT_FIELDS_NOT_TO_MODIFY_ON_UPDATE = Arrays.asList(new String[] {"sessionId", "apartmentId", "isDeleted", "isActive", "createdBy", "createdDate", "modifiedBy", "modifiedDate"});
	
	
	public static final String NOTE_TYPE_NOTE = "NOTE";
	public static final String NOTE_TYPE_SYSTEM = "SYSTEM";

	public static final String PAYMENT_MODE_CASH = "CASH";
	public static final String PAYMENT_MODE_ONLINE = "ONLINE";
	public static final String PAYMENT_MODE_CHEQUE = "CHEQUE";
	
	public static final String SADMIN_USER_NAME = "Super Admin";
	
	public static final String USER_ROLE_SADMIN = "SADMIN";
	public static final String USER_ROLE_USER = "USER";
	public static final String USER_ROLE_ADMIN = "ADMIN";
	public static final String USER_ROLE_FOUNDER = "FOUNDER";
	public static final String USER_ROLE_CEO = "CEO";
	public static final String USER_ROLE_GENERAL_MANAGER = "GENERAL-MANAGER";
	public static final String USER_ROLE_BRANCH_HEAD = "BRANCH-HEAD";
	public static final String USER_ROLE_HEAD_OF_OPERATION_AND_LOGISTICS = "HEAD-OF-OPERATION-AND-LOGISTICS";
	public static final String USER_ROLE_OPERATION_MANAGER = "OPERATION-MANAGER";
	public static final String USER_ROLE_FLOOR_MANAGER = "FLOOR-MANAGER";
	public static final String USER_ROLE_ASSISTANT_FLOOR_MANAGER = "ASSISTANT-FLOOR-MANAGER";
	public static final String USER_ROLE_SUPERVISOR = "SUPERVISOR";
	public static final String USER_ROLE_ASSISTANT_SUPERVISOR = "ASSISTANT-SUPERVISOR";
	public static final String USER_ROLE_SOFTWARE_DEVELOPER = "SOFTWARE-DEVELOPER";
	public static final String USER_ROLE_QC_HEAD = "QC-HEAD";
	public static final String USER_ROLE_SENIOR_QC = "SENIOR-QC";
	public static final String USER_ROLE_INTERMEDIATE_QC = "INTERMEDIATE-QC";
	public static final String USER_ROLE_QC = "QC";
	public static final String USER_ROLE_SENIOR_DATA_ANALYST = "SENIOR-DATA-ANALYST";
	public static final String USER_ROLE_INTERMEDIATE_DATA_ANALYST = "INTERMEDIATE-DATA-ANALYST";
	public static final String USER_ROLE_DATA_ANALYST = "DATA-ANALYST";
	public static final String USER_ROLE_TRANSPORTATION_STAFF = "TRANSPORTATION-STAFF";
	public static final String USER_ROLE_MAINTENANCE_STAFF = "MAINTENANCE-STAFF";

	
	public static final String SADMIN_CONTACT_NO = "1111111111";
	public static final String USER_DEFAULT_PASSWORD = "pass";
	
	public static final String USER_PERMISSION_ADD = "ADD";
	public static final String USER_PERMISSION_VIEW = "VIEW";
	public static final String USER_PERMISSION_CHILD_VIEW = "CHILD-VIEW";
	public static final String USER_PERMISSION_EDIT = "EDIT";
	public static final String USER_PERMISSION_DELETE = "DELETE";
	
	
	public static final String FILE_TYPE_IMAGE = "IMAGE";
	public static final String FILE_TYPE_DOCUMENT = "DOCUMENT";
	
	private static final List<String> FILE_EXTENSION_IMAGE = Arrays.asList(new String[] {"jpeg", "png", "gif", "jpg"});
	private static final List<String> FILE_EXTENSION_DOCUMENT = Arrays.asList(new String[] {"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "rtf", "txt", "jpeg", "png", "gif", "jpg"});
	
	public static final HashMap<String, List<String>> FILE_EXTENSIONS = new HashMap<>();
	
	static {
		FILE_EXTENSIONS.put(FILE_TYPE_IMAGE, FILE_EXTENSION_IMAGE);
		FILE_EXTENSIONS.put(FILE_TYPE_DOCUMENT, FILE_EXTENSION_DOCUMENT);
	}
	
	public static final String USER_UPLOAD_IMAGE_TYPE_PROFILE = "PROFILE";
	public static final String USER_UPLOAD_IMAGE_TYPE_SIGNATURE = "SIGNATURE";
	public static final String ORG_UPLOAD_IMAGE_TYPE_LOGO = "LOGO";
	
	private static final String USER_UPLOAD_PROFILE_IMAGE_PATH = "/user_profile_pictures";
	private static final String USER_UPLOAD_SIGNATURE_IMAGE_PATH = "/user_signatures";
	private static final String ORG_UPLOAD_LOGO_IMAGE_PATH = "/org_logos";
	
	public static final HashMap<String, String> USER_UPLOAD_IMAGE_PATHS = new HashMap<>();
	public static final HashMap<String, String> ORG_UPLOAD_IMAGE_PATHS = new HashMap<>();
	
	static {
		USER_UPLOAD_IMAGE_PATHS.put(USER_UPLOAD_IMAGE_TYPE_PROFILE, USER_UPLOAD_PROFILE_IMAGE_PATH);
		USER_UPLOAD_IMAGE_PATHS.put(USER_UPLOAD_IMAGE_TYPE_SIGNATURE, USER_UPLOAD_SIGNATURE_IMAGE_PATH);
		ORG_UPLOAD_IMAGE_PATHS.put(ORG_UPLOAD_IMAGE_TYPE_LOGO, ORG_UPLOAD_LOGO_IMAGE_PATH);
	}

	
	public static final String EMPLOYEE_LEAVES_STATUS_APPROVED = "APPROVED";
	public static final String EMPLOYEE_LEAVES_STATUS_PENDING = "PENDING";
	public static final String EMPLOYEE_LEAVES_STATUS_DECLINED = "DECLINED";
	public static final List<String> EMPLOYEE_LEAVES_STATUSES = Arrays.asList(new String[] {
			EMPLOYEE_LEAVES_STATUS_APPROVED, EMPLOYEE_LEAVES_STATUS_PENDING, EMPLOYEE_LEAVES_STATUS_DECLINED
	});
	
//	public static final String EMPLOYEE_LEAVES_TYPE_CL = "CL";
//	public static final String EMPLOYEE_LEAVES_TYPE_PL = "PL";
//	public static final List<String> EMPLOYEE_LEAVES_TYPES = Arrays.asList(new String[] { EMPLOYEE_LEAVES_TYPE_CL, EMPLOYEE_LEAVES_TYPE_PL });

	public static final String OBJECT_USER_DETAILS = "user-details";
	public static final String OBJECT_EMPLOYEE_DETAILS = "employee-details";
	public static final String OBJECT_DOCUMENTS = "documents";
	public static final String OBJECT_BIOMETRIC_ATTENDANCE = "biometric-attendance";
	public static final String OBJECT_EMPLOYEE_ATTENDENCE = "employee-attendence";
	public static final String OBJECT_EMPLOYEE_SALARY = "employee-salary";
	public static final String OBJECT_EMPLOYEE_SALARY_DETAILS = "employee-salary-details";
	public static final String OBJECT_EMPLOYEE_MONTHLY_PAYABLE_SALARY = "employee-monthly-payable-salary";
	public static final String OBJECT_EMPLOYEE_MONTHLY_PAYABLE_SALARY_DETAILS = "employee-monthly-payable-salary-details";
	public static final String OBJECT_BANK_DETAILS = "bank-details";
	public static final String OBJECT_MACHINE_DETAILS = "machine-details";
	public static final String OBJECT_EMP_MACHINE_DETAILS = "emp-machine-details";
	public static final String OBJECT_SESSION_DETAILS = "session-details";
	public static final String OBJECT_USER_ROLE_PERMISSION = "user-role-permission";
	public static final String OBJECT_EMPLOYEE_LEAVES = "employee-leaves";
	public static final String OBJECT_EMPLOYEE_LEAVES_DETAILS = "employee-leaves-details";
	public static final String OBJECT_M_EMPLOYEE_LEAVES = "m-employee-leaves";
	public static final String OBJECT_M_DESIGNATED_HOLIDAYS = "m-designated-holidays";
	public static final String OBJECT_NOTES = "notes";
	public static final String OBJECT_ORG_DETAILS = "org-details";
	public static final String OBJECT_DOCU_SIGN_USERS = "docu-sign-users";
	
	public static final List<String> OBJECT_LIST = 
			Arrays.asList(OBJECT_USER_DETAILS, OBJECT_EMPLOYEE_DETAILS, OBJECT_DOCUMENTS, OBJECT_BIOMETRIC_ATTENDANCE, OBJECT_EMPLOYEE_ATTENDENCE,
					OBJECT_EMPLOYEE_SALARY, OBJECT_EMPLOYEE_SALARY_DETAILS, OBJECT_EMPLOYEE_MONTHLY_PAYABLE_SALARY, OBJECT_EMPLOYEE_MONTHLY_PAYABLE_SALARY_DETAILS, OBJECT_BANK_DETAILS,
					OBJECT_MACHINE_DETAILS, OBJECT_EMP_MACHINE_DETAILS, OBJECT_SESSION_DETAILS, OBJECT_USER_ROLE_PERMISSION, OBJECT_EMPLOYEE_LEAVES, OBJECT_EMPLOYEE_LEAVES_DETAILS,
					OBJECT_M_EMPLOYEE_LEAVES, OBJECT_M_DESIGNATED_HOLIDAYS, OBJECT_NOTES, OBJECT_ORG_DETAILS, OBJECT_DOCU_SIGN_USERS
				);
	
}
