package com.intellifleet.entity;

import com.intellifleet.annotations.DTOMap;
import com.intellifleet.dto.CustomerMasterDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@DTOMap(listView = CustomerMasterDTO.class)
@Entity
@Table(name = "table_customer_master", schema = "admin",
        uniqueConstraints = @UniqueConstraint(name = "uk1_customer_master", columnNames = "int_customer_uid"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_wid")
    private Long rowWid;

    @Column(name = "int_customer_uid")
    private Long customerUid;

    @Column(name = "txt_customer_name")
    private String customerName;

    @Column(name = "int_manager_customer_id")
    private Integer managerCustomerId;

    @Column(name = "int_customer_cluster_uid")
    private Integer customerClusterId;

    @Column(name = "txt_relation_type")
    private String relationType;

    @Column(name = "txt_customer_authentication")
    private String customerAuthentication;

    @Column(name = "tms_create", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;

    @Column(name = "tms_update")
    private LocalDateTime updatedTime;

    @Column(name = "txt_active_row", columnDefinition = "text default 'ACTIVE'")
    private String activeRow;

    @Column(name = "txt_note")
    private String note;
}

