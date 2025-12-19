//package com.saas.projectDelivery.event;
//
//import com.saas.projectDelivery.config.RabbitMQProperties;
//import com.saas.projectDelivery.dto.eventDto.ReleaseResourceDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class ReleaseResourceEventConsumer {
//
//    private final RabbitMQProperties properties;
//
//    @RabbitListener(queues = "#{properties.releaseResourceQueue()}")
//    public void consumeReleaseResourceEvent(ReleaseResourceDto dto) {
//        try {
//            log.info("Received resource release event: {}", dto);
//
//            log.info("Successfully processed resource release event");
//        } catch (Exception e) {
//            log.error("Failed to process resource release event", e);
//        }
//    }
//}