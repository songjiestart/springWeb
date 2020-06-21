package com.ceshi.springboot;

import com.ceshi.springboot.mq.RabbitMqProducer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
    private RabbitTemplate rabbitTemplate;

    @Test
    void consume() {
        Object o = rabbitTemplate.receiveAndConvert("songjie.queue2");

        System.out.println(o.getClass());
        System.out.println(o);

    }

}
