package com.intellifleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CoreMatrixViewId implements Serializable {

    @Column(name = "txt_instrument_id")
    private String instrumentId;

    @Column(name = "int_customer_uid")
    private Long customerUid;

    @Column(name = "int_commodity_id")
    private Long commodityId;
}
