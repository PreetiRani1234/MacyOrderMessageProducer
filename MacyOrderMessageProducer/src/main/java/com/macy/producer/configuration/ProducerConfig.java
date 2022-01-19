package com.macy.producer.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.macy.producer.util.AppConstant;

@Configuration
public class ProducerConfig {

    @Bean
    Queue jsonQueue() {
        return new Queue(AppConstant.JSON_QUEUE, true);
    }

    @Bean
    Queue xmlQueue() {
        return new Queue(AppConstant.XML_QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(AppConstant.EXCHANGE);
    }

    @Bean
    Binding binding1(DirectExchange exchange) {
        return BindingBuilder.bind(jsonQueue()).to(exchange).with(jsonQueue().getName());
    }

    @Bean
    Binding binding2(DirectExchange exchange) {
        return BindingBuilder.bind(xmlQueue()).to(exchange).with(xmlQueue().getName());
    }

    @Bean
    public AmqpTemplate jsonAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AppConstant.ROUTING_KEY);
        rabbitTemplate.setMessageConverter(new Jackson2XmlMessageConverter());
        rabbitTemplate.setDefaultReceiveQueue(jsonQueue().getName());
        return rabbitTemplate;
    }

    @Bean
    public AmqpTemplate xmlAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AppConstant.ROUTING_KEY);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setDefaultReceiveQueue(xmlQueue().getName());
        return rabbitTemplate;
    }

}