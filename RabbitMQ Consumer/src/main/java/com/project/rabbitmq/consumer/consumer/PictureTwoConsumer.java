package com.project.rabbitmq.consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rabbitmq.consumer.entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureTwoConsumer {

    private static final Logger log = LoggerFactory.getLogger(PictureTwoConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.filter", "q.picture.log"})
    public void listen(Message message) throws JsonProcessingException {
        String jsonString = new String(message.getBody());
        Picture picture = objectMapper.readValue(jsonString, Picture.class);

        log.info("Consuming: {} with routing key: {}", picture, message.getMessageProperties().getReceivedRoutingKey());
    }
}
