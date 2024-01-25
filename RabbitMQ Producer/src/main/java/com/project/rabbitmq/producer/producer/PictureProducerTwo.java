package com.project.rabbitmq.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rabbitmq.producer.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducerTwo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(picture);
        StringBuilder routingKeyBuilder = new StringBuilder();

        routingKeyBuilder.append(picture.getSource());
        routingKeyBuilder.append(".");

        if(picture.getSize() > 400){
            routingKeyBuilder.append("large");
        } else {
            routingKeyBuilder.append("small");
        }
        routingKeyBuilder.append(".");

        routingKeyBuilder.append(picture.getType());
        String routingKey = routingKeyBuilder.toString();

        rabbitTemplate.convertAndSend("x.picture2", routingKey, json);
    }
}
