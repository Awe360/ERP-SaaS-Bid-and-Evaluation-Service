package com.saas.projectDelivery.event;

import com.saas.projectDelivery.config.RabbitMQProperties;
import com.saas.projectDelivery.dto.eventDto.ChangeActivityStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeActivityStatusEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;

    public void sendChangeActivityStatusEvent(ChangeActivityStatusDto dto) {
        try {
            rabbitTemplate.convertAndSend(
                    properties.exchange(),
                    properties.changeActivityStatusQueue(),
                    dto
            );
            log.info("Published activity status change event");
        } catch (Exception e) {
            log.error("Failed to publish activity status change event", e);
        }
    }
}