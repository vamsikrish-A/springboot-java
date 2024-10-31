package com.example.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.dto.User;

@Service
public class RabbitMqJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void jsonConsumer(User user) {
        LOGGER.info(String.format("Received JSON message -> %s", user.toString()));

    }

}
