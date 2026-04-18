package com.intellifleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_user_master", schema = "admin",
        uniqueConstraints = @UniqueConstraint(name = "uk1_user_master", columnNames = "txt_user_uid"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_wid")
    private Long id;

    @Column(name = "txt_user_uid")
    private String userUid;

    @Column(name = "txt_user_name")
    private String userName;

    @Column(name = "txt_user_password")
    private String userPassword;

    @Column(name = "int_customer_uid")
    private Long customerUid;
}

