package com.example.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.dto.User;
import com.example.rabbitmq.publisher.RabbitMqJsonProducer;

@RestController
@RequestMapping("api/v2")
public class MessageJsonController {

    @Autowired
    private RabbitMqJsonProducer rabbitMqJsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitMqJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json messsage sent to RabbitMQ...");

    }

}
