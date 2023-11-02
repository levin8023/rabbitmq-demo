package com.example.rabbitmqdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitmqdemo.RabbitMQConfig.FANOUT_QUEUE1;

/**
 * @author Levin
 */
@Component
@Slf4j
@RabbitListener(queues = FANOUT_QUEUE1)
public class RabbitMQConsumer {

    @RabbitHandler
    public void receive(Message message) {
        log.info("RabbitMQConsumer receive ------>  : {} ", message);
    }

}
