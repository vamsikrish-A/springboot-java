package com.example.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueString;

    @Value("${rabbitmq.queue.json.name}")
    private String queueJson;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    //spring bean for rabbitMQ queue
    @Bean
    public Queue queue() {
        return new Queue(queueString);
    }

    //spring bean for rabbitMQ exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    //Binding between Queue and Exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                        .to(exchange())
                        .with(routingKey);
    }

    //connectionFactory
    //RabbitTemplate
    //rabbitAdmin , Spring will automatically config these beans, we no need to config explicitley  

    // spring bean to store the json messeges
    @Bean
    public Queue queueJson() {
        return new Queue(queueJson);
    }
    //Binding between Json Queue and Exchange using routing key
    @Bean
    public Binding bindingJson() {
        return BindingBuilder.bind(queueJson()).to(exchange()).with(routingJsonKey);
    }

    @Bean
    public MessageConverter convertor() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(convertor());
        return rabbitTemplate;
    }
}
