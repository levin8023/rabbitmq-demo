package com.example.rabbitmqdemo.producer;

import com.example.rabbitmqdemo.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;


    @SneakyThrows
    public String send(Message msg) {
        msg.setSendTime(new Date());
        rabbitTemplate.convertAndSend(msg.getExchangeName(), msg.getRoutingKey(), msg);
        log.info("RabbitMQProducer send: {}", msg);
        return "successfully";
    }
}
