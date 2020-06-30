package com.ceshi.springboot;

import com.ceshi.springboot.mq.RabbitMqProducer;
import com.rabbitmq.client.AMQP;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootApplicationTests {

    @Resource
    private RabbitMqProducer rabbitMqProducer;
    @Resource
    private AmqpAdmin rabbitAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void topic() {
        rabbitAdmin.declareBinding(new Binding("admin.queue", Binding.DestinationType.QUEUE, "admin.direct", "admin", Maps.newHashMap("key", "value")));

    }
    @Test
    void consume() {
        Object o = rabbitTemplate.receiveAndConvert("songjie.queue2");

        System.out.println(o.getClass());
        System.out.println(o);

    }

}
