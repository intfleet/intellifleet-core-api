package com.intellifleet.entity;
import com.intellifleet.constants.AppConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@Entity
@Immutable
@Table(name = AppConstants.VIEW_MVIEW_CORE_MATRIX, schema = AppConstants.DB_SCHEMA_NAME_CORE)
public class CoreMatrixView {

    @EmbeddedId
    private CoreMatrixViewId id;

    @Column(name = "txt_resources")
    private String resources;

    @Column(name = "txt_resources_alias")
    private String resourcesAlias;

    @Column(name = "txt_resources_name")
    private String resourcesName;

    @Column(name = "txt_incident")
    private String incident;

    @Column(name = "txt_customer_name")
    private String customerName;

    @Column(name = "txt_customer_type")
    private String customerType;

    @Column(name = "txt_node_uid")
    private String nodeUid;

    @Column(name = "txt_commodity_name")
    private String commodityName;

    @Column(name = "txt_commodity_authentication")
    private String commodityAuthentication;

    @Column(name = "txt_pattern_id")
    private String patternId;

    @Column(name = "txt_sim_number")
    private String simNumber;

    @Column(name = "txt_manufacture_id")
    private String manufactureId;

    @Column(name = "txt_invoice")
    private String invoice;

    @Column(name = "tms_invoice")
    private java.time.LocalDateTime tmsInvoice;

    @Column(name = "tms_purchase_disposition")
    private java.time.LocalDateTime tmsPurchaseDisposition;

    @Column(name = "tms_instrument_get")
    private java.time.LocalDateTime tmsInstrumentGet;

    @Column(name = "txt_version_no")
    private String versionNo;

    @Column(name = "txt_instrument_status")
    private String instrumentStatus;

    @Column(name = "txt_pattern_description")
    private String patternDescription;

    @Column(name = "txt_database_name")
    private String databaseName;

    @Column(name = "txt_database_type")
    private String databaseType;

    @Column(name = "int_database_port")
    private Integer databasePort;

    @Column(name = "txt_database_host")
    private String databaseHost;

    @Column(name = "txt_database_user")
    private String databaseUser;

    @Column(name = "txt_database_password")
    private String databasePassword;

    @Column(name = "txt_database_link")
    private String databaseLink;

    @Column(name = "txt_database_owner")
    private String databaseOwner;

    @Column(name = "txt_user_id")
    private String userId;

    @Column(name = "txt_user_password")
    private String userPassword;

    @Column(name = "txt_user_name")
    private String userName;

    @Column(name = "int_project_uid")
    private Long projectUid;

    @Column(name = "txt_project_name")
    private String projectName;

    @Column(name = "txt_project_db_uid")
    private String projectDbUid;

    @Column(name = "txt_project_db_name")
    private String projectDbName;

    @Column(name = "int_project_db_port")
    private Integer projectDbPort;

    @Column(name = "txt_project_db_host")
    private String projectDbHost;

    @Column(name = "txt_project_db_user")
    private String projectDbUser;

    @Column(name = "txt_project_db_password")
    private String projectDbPassword;

    @Column(name = "txt_project_db_link")
    private String projectDbLink;

    @Column(name = "txt_project_db_owner")
    private String projectDbOwner;
}

