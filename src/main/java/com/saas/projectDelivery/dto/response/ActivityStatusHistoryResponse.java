package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityStatusHistoryResponse extends BaseResponse {

    private UUID projectId;
    private UUID subProjectId;
    private UUID activityId;
    private String oldStatus;
    private String newStatus;
    private String comment;

}