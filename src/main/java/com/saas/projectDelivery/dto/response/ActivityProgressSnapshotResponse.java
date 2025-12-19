package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityProgressSnapshotResponse extends BaseResponse {

    private UUID programmeId;
    private UUID projectId;
    private UUID subProjectId;
    private UUID activityId;
    private LocalDate progressDate;
    private BigDecimal currentProgress;
    private String critical;
    private String description;

    private UUID activityProgressDocumentId;
    private String activityProgressFileName;
    private String activityProgressFileType;
    private Long activityProgressFileSize;
}