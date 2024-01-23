package com.project.rabbitmq.consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rabbitmq.consumer.entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureImageConsumer {

    private static final Logger log = LoggerFactory.getLogger(PictureImageConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws JsonProcessingException {
        Picture picture = objectMapper.readValue(message, Picture.class);

        log.info("Image is {}", picture);
    }
}
