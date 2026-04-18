package com.intellifleet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "table_client_group_child",
        schema = "admin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk1_client_group_child",
                        columnNames = {"int_customer_cluster_uid", "txt_db_code"}
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientGroupChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_wid", nullable = false)
    private Integer rowWid;

    @Column(name = "int_customer_cluster_uid")
    private Integer customerClusterID;

    @Column(name = "txt_db_code")
    private String dbCode;

    @Column(name = "txt_database_name")
    private String databaseName;

    @Column(name = "txt_database_type")
    private String databaseType;

    @Column(name = "int_port")
    private Integer port;

    @Column(name = "txt_host")
    private String host;

    @Column(name = "txt_user")
    private String user;

    @Column(name = "txt_password")
    private String password;

    @Column(name = "txt_db_link")
    private String dbLink;

    @Column(name = "txt_owner")
    private String owner;

    @Column(name = "tms_create", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private LocalDateTime tmsCreate;

    @Column(name = "tms_update")
    private LocalDateTime tmsUpdate;

    @Column(name = "txt_active_row", columnDefinition = "text default 'ACTIVE'")
    private String activeRow;

    @Column(name = "txt_note")
    private String note;
}
