package com.springexamples.springamqp_support_ticket.controller;

import com.springexamples.springamqp_support_ticket.service.AmqpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@AllArgsConstructor
public class DefaultController {
    private final AmqpService amqpService;

    @PostMapping("sendMessage")
    void sendMessage(String message) {
        amqpService.sendMessageToQueue(message);
    }
}
