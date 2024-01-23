package com.project.rabbitmq.producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rabbitmq.producer.entity.Employee;
import com.project.rabbitmq.producer.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(picture);
        rabbitTemplate.convertAndSend("x.picture", picture.getType(), json);
    }
}
