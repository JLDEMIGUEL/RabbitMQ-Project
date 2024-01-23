package com.project.rabbitmq.producer;

import com.project.rabbitmq.producer.entity.Employee;
import com.project.rabbitmq.producer.entity.Picture;
import com.project.rabbitmq.producer.producer.HelloRabbitProducer;
import com.project.rabbitmq.producer.producer.HumanResourceProducer;
import com.project.rabbitmq.producer.producer.PictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@SpringBootApplication
public class RabbitMqProducerApplication implements CommandLineRunner {

    @Autowired
    private PictureProducer pictureProducer;

    private final List<String> SOURCES = List.of("mobile","web");

    private final List<String> TYPES = List.of("jpg", "png", "svg");

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Picture p = new Picture("Picture " + i,
                    TYPES.get(i % TYPES.size()),
                    SOURCES.get(i % SOURCES.size()),
                    ThreadLocalRandom.current().nextLong(1, 1000));
            pictureProducer.sendMessage(p);
        }
    }
}
