package com.saas.projectDelivery.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activity_status_histories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityStatusHistory extends Base {

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @Column(name = "activity_id", nullable = false)
    private UUID activityId;

    @Column(name = "old_status", length = 50)
    private String oldStatus;

    @Column(name = "new_status", length = 50)
    private String newStatus;

    @Column(columnDefinition = "TEXT")
    private String comment;
}