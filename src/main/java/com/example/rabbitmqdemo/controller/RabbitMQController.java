package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.model.Message;
import com.example.rabbitmqdemo.producer.RabbitMQProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import static com.example.rabbitmqdemo.config.RabbitMQConfig.Direct.DIRECT_EXCHANGE;
import static com.example.rabbitmqdemo.config.RabbitMQConfig.Direct.DIRECT_QUEUE_1;
import static com.example.rabbitmqdemo.config.RabbitMQConfig.Fanout.FANOUT_EXCHANGE;
import static com.example.rabbitmqdemo.config.RabbitMQConfig.Topic.TOPIC_EXCHANGE_NAME;

/**
 * @author Levin
 */
@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor
@Tag(name = "RabbitMQ消息推送")
public class RabbitMQController {
    private final RabbitMQProducer rabbitMQProducer;

    @SneakyThrows
    @PostMapping("/send-direct")
    @Operation(summary = "发送点对点消息")
    public String sendDirect(@RequestBody String content) {
        Message msg = new Message();
        msg.setContent(content);
        msg.setRoutingKey(DIRECT_QUEUE_1);
        msg.setExchangeName(DIRECT_EXCHANGE);
        return rabbitMQProducer.send(msg);
    }

    @SneakyThrows
    @PostMapping("/send-fanout")
    @Operation(summary = "发送订阅消息")
    public String sendFanout(@RequestBody String content) {
        Message msg = new Message();
        msg.setContent(content);
        msg.setExchangeName(FANOUT_EXCHANGE);
        return rabbitMQProducer.send(msg);
    }

    @SneakyThrows
    @PostMapping("/send-topic/{routing-key}")
    @Operation(summary = "发送主题消息")
    public String sendTopic(@RequestBody String content,
                            @PathVariable("routing-key")
                            @Schema(description = "消息路由", defaultValue = "a.1", examples = {"a.1", "b.1", "c.1", "c.1.1"})
                            String routingKey) {
        Message msg = new Message();
        msg.setContent(content);
        msg.setExchangeName(TOPIC_EXCHANGE_NAME);
        msg.setRoutingKey(routingKey);
        return rabbitMQProducer.send(msg);
    }
}
