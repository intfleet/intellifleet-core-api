package com.intellifleet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "columns", schema = "information_schema")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldEntity {

    /* generated in sql SELECT query ( row_number() OVER () AS id )
       code implemented on FormServiceImpl.getFormDetails()
    */
    @Id
    private Long id;

    @Column(name = "table_schema")
    private String tableSchema;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "character_maximum_length")
    private Integer characterMaximumLength;

    @Column(name = "is_nullable")
    private String nullable;

    @Column(name = "constraint_type")
    private String constraintType;

    @Column(name = "constraint_name")
    private String constrainName;

    @Column(name = "foreign_table")
    private String foreignTable;

    @Column(name = "foreign_column")
    private String foreignColumn;
}
