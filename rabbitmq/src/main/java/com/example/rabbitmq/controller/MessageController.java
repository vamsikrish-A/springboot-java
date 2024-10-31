package com.example.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.publisher.RabbitMQProducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    
    @GetMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent to RabbitMq...");
    }

}
