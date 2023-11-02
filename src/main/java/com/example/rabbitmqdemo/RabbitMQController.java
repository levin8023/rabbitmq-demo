package com.example.rabbitmqdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQController {
    private final RabbitMQProducer rabbitMQProducer;

    @SneakyThrows
    @PostMapping("/send")
    public String send(@RequestBody Message msg) {
        return rabbitMQProducer.send(msg);
    }
}
