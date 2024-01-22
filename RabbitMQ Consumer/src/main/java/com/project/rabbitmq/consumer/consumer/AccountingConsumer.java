package com.project.rabbitmq.consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rabbitmq.consumer.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingConsumer {

    private static final Logger log = LoggerFactory.getLogger(AccountingConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.hr.accounting")
    public void listen(String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);

        log.info("On accounting: {}", employee);
    }
}
