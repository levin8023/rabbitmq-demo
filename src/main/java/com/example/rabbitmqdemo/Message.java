package com.example.rabbitmqdemo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Levin
 */
@Data
public class Message implements Serializable {
    private String content;
    private String sender;
    private Date sendTime;
}
