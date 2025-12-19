package com.saas.projectDelivery.dto.eventDto;

import lombok.Data;
import java.util.UUID;

@Data
public class ChangeProjectStatusDtoConsumer {
    private UUID projectId;
    private UUID tenantId;
    private String previousStatus;
    private String newStatus;
}