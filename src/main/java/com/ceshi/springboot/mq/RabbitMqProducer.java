package com.ceshi.springboot.mq;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhaisongjie
 * @Date: 2020-06-21 18:28
 * @Description:
 */
@Service
public class RabbitMqProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void producer(String exchange, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


}
