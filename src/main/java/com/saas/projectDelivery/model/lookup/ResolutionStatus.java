package com.saas.projectDelivery.model.lookup;

import com.saas.projectDelivery.model.Base;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "resolution_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolutionStatus extends Base {

    @Column(name = "status_name", nullable = false, unique = true, length = 100)
    private String statusName;
}