package com.ceshi.springboot.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhaisongjie
 * @Date: 2020-06-21 18:28
 * @Description:
 */
@Slf4j
@Service
public class RabbitMqConsumer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public String receive(String queueName) {
        Object o = rabbitTemplate.receiveAndConvert(queueName);

        return JSON.toJSONString(o);

    }

    @RabbitListener(queues = {"songjie.queue","songjie.queue2"})
    public void consume(String param) {
        log.info("consume start param:{}", param);
    }


}
