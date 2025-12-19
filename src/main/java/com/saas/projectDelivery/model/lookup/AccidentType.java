package com.saas.projectDelivery.model.lookup;

import com.saas.projectDelivery.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data

public class AccidentType extends Base{
   @Column(name = "type_name", nullable = false, unique = true, length = 100)
    private String typeName;

}
