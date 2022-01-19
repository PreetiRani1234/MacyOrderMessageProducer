package com.macy.producer.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macy.producer.dto.json.ProducerDto;
import com.macy.producer.dto.xml.FulfillmentOrder;

@Component
public class ProducerComponent {

    @Autowired
    AmqpTemplate xmlAmqpTemplate;

    @Autowired
    AmqpTemplate jsonAmqpTemplate;

    @Autowired
    private Queue jsonQueue;

    @Autowired
    private Queue xmlQueue;

   

    public void send(FulfillmentOrder fulfillmentOrder) throws AmqpException {
        jsonAmqpTemplate.convertAndSend(xmlQueue.getName(), fulfillmentOrder);
    }

    public void send(ProducerDto producerDto) throws AmqpException {
        xmlAmqpTemplate.convertAndSend(jsonQueue.getName(), producerDto);
    }

}