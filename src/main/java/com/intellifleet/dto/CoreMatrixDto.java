package com.intellifleet.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder             // ✅ Optional but very useful
@ToString
public class CoreMatrixDto implements Serializable {

    private String instrumentId;
    private Long commodityId;
    private Long customerUid;

    private String resources;
    private String resourcesAlias;
    private String resourcesName;
    private String incident;

    private String customerName;
    private String customerType;

    private String nodeUid;

    private String commodityName;
    private String commodityAuthentication;

    private String patternId;
    private String simNumber;
    private String manufactureId;
    private String invoice;

    private LocalDateTime tmsInvoice;
    private LocalDateTime tmsPurchaseDisposition;
    private LocalDateTime tmsInstrumentGet;

    private String versionNo;
    private String instrumentStatus;
    private String patternDescription;

    private String databaseName;
    private String databaseType;
    private Integer databasePort;
    private String databaseHost;
    private String databaseUser;
    private String databaseLink;
    private String databaseOwner;

    private String userId;
    private String userName;

    private Long projectUid;
    private String projectName;

    private String projectDbUid;
    private String projectDbName;
    private Integer projectDbPort;
    private String projectDbHost;
    private String projectDbUser;
    private String projectDbLink;
    private String projectDbOwner;
}
