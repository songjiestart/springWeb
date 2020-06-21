package com.ceshi.springboot.controller;

import com.ceshi.springboot.mq.RabbitMqConsumer;
import com.ceshi.springboot.mq.RabbitMqProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhaisongjie
 * @Date: 2020-06-21 18:40
 * @Description:
 */
@RequestMapping("/door")
@RestController
public class DoorController {
    @Resource
    private RabbitMqConsumer rabbitMqConsumer;

    @Resource
    private RabbitMqProducer rabbitMqProducer;


    @RequestMapping("/producer")
    public String producer(String exchange, String routingKey, String message) {
        rabbitMqProducer.producer(exchange, routingKey, message);
        return "success";

    }

    @RequestMapping("/consume")
    public String consume(String queueName) {
        return rabbitMqConsumer.consume(queueName);
    }
}
