package com.saas.projectDelivery.model.lookup;

import com.saas.projectDelivery.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "currencies")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Currency extends Base {

    @Column(name = "currency_code", nullable = false, unique = true, length = 10)
    private String currencyCode;

    @Column(name = "currency_name", nullable = false, length = 100)
    private String currencyName;

    @Column(name = "symbol", length = 10)
    private String symbol;

    @Column(nullable = false)
    private boolean active = true;


}
