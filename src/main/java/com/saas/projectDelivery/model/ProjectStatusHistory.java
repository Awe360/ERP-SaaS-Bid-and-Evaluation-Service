
package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_status_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProjectStatusHistory extends Base {

    @Column(nullable = false)
    private UUID projectId;

    @Column(nullable = false, length = 50)
    private String previousStatus;

    @Column(nullable = false, length = 50)
    private String newStatus;

    private String comment;



}