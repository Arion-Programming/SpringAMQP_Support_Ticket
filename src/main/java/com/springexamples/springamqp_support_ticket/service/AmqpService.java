package com.springexamples.springamqp_support_ticket.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmqpService {
    private final AmqpTemplate amqpTemplate;

    public void sendMessageToQueue(String message) {
        amqpTemplate.convertAndSend(message);
        System.out.println(message);
    }
    @RabbitListener(queues = "hello.world.queue")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }

}
