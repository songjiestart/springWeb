package com.ceshi.springboot.controller;

import com.ceshi.springboot.mq.RabbitMqConsumer;
import com.ceshi.springboot.mq.RabbitMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhaisongjie
 * @Date: 2020-06-21 18:40
 * @Description:
 */
@Slf4j
@RequestMapping("/door")
@RestController
public class DoorController {
    @Resource
    private RabbitMqConsumer rabbitMqConsumer;

    @Resource
    private RabbitMqProducer rabbitMqProducer;

    @Resource
    private AmqpAdmin rabbitAdmin;


    @RequestMapping("/producer")
    public String producer(String exchange, String routingKey, String message) {
        log.info("producer start");
        rabbitMqProducer.producer(exchange, routingKey, message);
        return "success";

    }

    @RequestMapping("/consume")
    public String consume(String queueName) {
        log.info("consume start");
        return rabbitMqConsumer.receive(queueName);
    }

    @RequestMapping("/declareExchange")
    public String declareExchange(String name) {
        log.info("declareExchange start");
        rabbitAdmin.declareExchange(new DirectExchange(name));
        return "success";
    }

    @RequestMapping("/declareQueue")
    public String declareQueue(String name) {
        log.info("declareQueue start");
        rabbitAdmin.declareQueue(new Queue(name));
        return "success";
    }

    @RequestMapping("/declareBinding")
    public String declareBinding(String queueName, String exchange, String routingKey) {
        log.info("declareBinding start");
        rabbitAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchange, routingKey, null));
        return "success";
    }
}
