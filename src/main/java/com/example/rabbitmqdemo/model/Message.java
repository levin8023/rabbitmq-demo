package com.example.rabbitmqdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Levin
 */
@Data
@Schema(name = "Message", description = "消息对象")
public class Message implements Serializable {
    @Schema(description = "消息内容")
    private String content;
    @Schema(description = "发送人")
    private String sender;
    @Schema(description = "发送时间", hidden = true)
    private Date sendTime;
    @Schema(description = "消息交换机名称", hidden = true)
    private String exchangeName;
    @Schema(description = "消息路由（主题消息时需要）")
    private String routingKey;
}
