package com.saas.projectDelivery.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatusHistoryResponse extends BaseResponse {

    private UUID projectId;
    private String previousStatus;
    private String newStatus;
    private String comment;

}