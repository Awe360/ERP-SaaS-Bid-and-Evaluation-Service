package com.saas.projectDelivery.event;

import com.saas.projectDelivery.config.RabbitMQProperties;
import com.saas.projectDelivery.dto.eventDto.ReleaseResourceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReleaseResourceEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;

    public void sendReleaseResourceEvent(ReleaseResourceDto dto) {
        try {
            rabbitTemplate.convertAndSend(
                    properties.exchange(),
                    properties.releaseResourceQueue(),
                    dto
            );
            log.info("Published resource release event");
        } catch (Exception e) {
            log.error("Failed to publish resource release event", e);
        }
    }
}