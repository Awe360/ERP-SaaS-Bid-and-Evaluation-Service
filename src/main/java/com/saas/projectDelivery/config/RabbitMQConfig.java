package com.saas.projectDelivery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQProperties properties;
    private final ObjectMapper objectMapper;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(properties.exchange(), true, false);
    }

    @Bean
    public Queue changeProjectStatusQueue() {
        return QueueBuilder.durable(properties.changeProjectStatusQueue()).build();
    }

    @Bean
    public Queue changeActivityStatusQueue() {
        return QueueBuilder.durable(properties.changeActivityStatusQueue()).build();
    }

    @Bean
    public Queue releaseResourceQueue() {
        return QueueBuilder.durable(properties.releaseResourceQueue()).build();
    }


//    @Bean
//    public Queue createResourceQueue() {
//        return QueueBuilder.durable(properties.createResourceQueue()).build();
//    }
//
//    @Bean
//    public Queue deleteResourceQueue() {
//        return QueueBuilder.durable(properties.deleteResourceQueue()).build();
//    }
//
//    @Bean
//    public Queue changeResourceStatusQueue() {
//        return QueueBuilder.durable(properties.changeResourceStatusQueue()).build();
//    }

    @Bean
    public Binding changeProjectStatusBinding() {
        return BindingBuilder.bind(changeProjectStatusQueue())
                .to(exchange())
                .with(properties.changeProjectStatusQueue());
    }

    @Bean
    public Binding changeActivityStatusBinding() {
        return BindingBuilder.bind(changeActivityStatusQueue())
                .to(exchange())
                .with(properties.changeActivityStatusQueue());
    }

    @Bean
    public Binding releaseResourceBinding() {
        return BindingBuilder.bind(releaseResourceQueue())
                .to(exchange())
                .with(properties.releaseResourceQueue());
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jacksonMessageConverter());
        return template;
    }

//    @Bean
//    public Binding createResourceBinding() {
//        return BindingBuilder.bind(createResourceQueue())
//                .to(exchange())
//                .with(properties.createResourceQueue());
//    }
//
//    @Bean
//    public Binding deleteResourceBinding() {
//        return BindingBuilder.bind(deleteResourceQueue())
//                .to(exchange())
//                .with(properties.deleteResourceQueue());
//    }
//
//    @Bean
//    public Binding changeResourceStatusBinding() {
//        return BindingBuilder.bind(changeResourceStatusQueue())
//                .to(exchange())
//                .with(properties.changeResourceStatusQueue());
//    }
}