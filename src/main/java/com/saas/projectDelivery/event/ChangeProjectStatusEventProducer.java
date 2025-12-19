package com.saas.projectDelivery.event;

import com.saas.projectDelivery.config.RabbitMQProperties;
import com.saas.projectDelivery.dto.eventDto.ChangeProjectStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeProjectStatusEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;

    public void sendChangeProjectStatusEvent(ChangeProjectStatusDto dto) {
        try {
            rabbitTemplate.convertAndSend(
                    properties.exchange(),
                    properties.changeProjectStatusQueue(),
                    dto
            );
            log.info("Published project status change event | Project: {} | {} to {}",
                    dto.getProjectId(), dto.getPreviousStatus(), dto.getNewStatus());
        } catch (Exception e) {
            log.error("Failed to publish status change event for project {}", dto.getProjectId(), e);
        }
    }
}