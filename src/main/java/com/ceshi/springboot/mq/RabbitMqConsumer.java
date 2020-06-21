package com.ceshi.springboot.mq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhaisongjie
 * @Date: 2020-06-21 18:28
 * @Description:
 */
@Service
public class RabbitMqConsumer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public String consume(String queueName) {
        Object o = rabbitTemplate.receiveAndConvert(queueName);

        return JSON.toJSONString(o);

    }


}
