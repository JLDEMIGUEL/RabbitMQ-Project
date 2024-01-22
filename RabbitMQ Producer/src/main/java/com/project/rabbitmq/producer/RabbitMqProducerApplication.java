package com.project.rabbitmq.producer;

import com.project.rabbitmq.producer.entity.Employee;
import com.project.rabbitmq.producer.producer.EmployeeJsonProducer;
import com.project.rabbitmq.producer.producer.HelloRabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@SpringBootApplication
public class RabbitMqProducerApplication implements CommandLineRunner {

    @Autowired
    private HelloRabbitProducer helloRabbitProducer;

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        helloRabbitProducer.sendHello("MyName " + ThreadLocalRandom.current().nextInt());

        for (int i = 0; i < 5; i++) {
            employeeJsonProducer.sendMessage(new Employee("emp-" + i, "Employee " + i, LocalDate.now()));
        }
    }
}
