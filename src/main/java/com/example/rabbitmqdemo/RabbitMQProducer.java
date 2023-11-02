package com.example.rabbitmqdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static com.example.rabbitmqdemo.RabbitMQConfig.FANOUT_EXCHANGE;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;


    @SneakyThrows
    public String send(Message msg) {
        msg.setSendTime(new Date());
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, "", msg);
        return "successfully";
    }
}
