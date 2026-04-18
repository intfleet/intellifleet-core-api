package com.intellifleet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_query")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_wid", nullable = false)
    private Long id;

    @Column(name = "txt_name", nullable = false)
    private String name;

    @Column(name = "txt_label", nullable = false)
    private String label;

    @Column(name = "txt_type", nullable = false, insertable = false)
    private String type;

    @Column(name = "txt_note")
    private String note;

    @Column(name = "txt_active_row", nullable = false, insertable = false)
    private String activeRow;

    @CreationTimestamp
    @Column(name = "tms_create")
    private LocalDateTime tmsCreate;

    @UpdateTimestamp
    @Column(name = "tms_update")
    private LocalDateTime tmsUpdate;
}
