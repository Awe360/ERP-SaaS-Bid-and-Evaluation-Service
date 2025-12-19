package com.saas.projectDelivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitMQProperties(
        String exchange,
        String changeProjectStatusQueue,
        String changeActivityStatusQueue,
        String releaseResourceQueue
//        String createResourceQueue,
//        String deleteResourceQueue,
//        String changeResourceStatusQueue

) {}