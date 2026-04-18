package com.intellifleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_client_group", schema = "admin",
        uniqueConstraints = @UniqueConstraint(name = "uk1_client_group", columnNames = "int_customer_cluster_uid"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_wid")
    private Long rowWid;

    @Column(name = "int_customer_cluster_uid")
    private Long customerClusterID;

    @Column(name = "txt_customer_cluster_name")
    private String customerClusterName;

    @Column(name = "tms_create", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime tmsCreate;

    @Column(name = "tms_update")
    private LocalDateTime tmsUpdate;

    @Column(name = "txt_active_row", columnDefinition = "text default 'ACTIVE'")
    private String activeRow;

    @Column(name = "txt_note")
    private String note;
}

