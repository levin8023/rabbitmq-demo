package com.example.rabbitmqdemo.consumer;

import com.example.rabbitmqdemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitmqdemo.config.RabbitMQConfig.Direct.DIRECT_QUEUE_1;
import static com.example.rabbitmqdemo.config.RabbitMQConfig.Fanout.FANOUT_QUEUE1;
import static com.example.rabbitmqdemo.config.RabbitMQConfig.Topic.*;

/**
 * @author Levin
 */
@Component
@Slf4j
@RabbitListener(queues = {DIRECT_QUEUE_1, FANOUT_QUEUE1, TOPIC_EXCHANGE_QUEUE_A, TOPIC_EXCHANGE_QUEUE_B, TOPIC_EXCHANGE_QUEUE_C})
public class RabbitMQConsumer {

    @RabbitHandler
    public void receive(Message message) {
        log.info("RabbitMQConsumer receive {}-{} ------>  : {} ",
                message.getExchangeName(), message.getRoutingKey(), message);
    }

}
