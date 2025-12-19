package com.saas.projectDelivery.dto.eventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseResourceDto {
    private UUID tenantId;
    private UUID projectId;
    private String message;
    private String releasedBy;
    private LocalDateTime releasedAt;

}
