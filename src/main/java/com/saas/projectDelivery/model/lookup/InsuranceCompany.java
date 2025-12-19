package com.saas.projectDelivery.model.lookup;

import com.saas.projectDelivery.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance_companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class InsuranceCompany extends Base {

    @Column(name = "company_name", nullable = false, unique = true, length = 200)
    private String companyName;

    @Column(length = 200)
    private String contactPerson;

    @Column(length = 100)
    private String phone;

    @Column(length = 200)
    private String email;

    private String website;

    private String address;



}
