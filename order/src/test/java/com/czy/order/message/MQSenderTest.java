package com.czy.order.message;

import com.czy.order.OrderApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MQSenderTest extends OrderApplicationTests {

     @Autowired
     AmqpTemplate amqpTemplate;

     @Value("${mq.exchange-name}")
     private String EXCHANGE_NAME;
     @Test
     public void sendComputer() {
          String msg = "【computer order】Send message time is : " + new Date();
          amqpTemplate.convertAndSend(EXCHANGE_NAME, "computer", msg);//交换机，routingKey，消息
          log.info("message : {}", msg);
     }

     @Test
     public void sendFruit() {
          String msg = "【Fruit order】Send message time is : " + new Date();
          amqpTemplate.convertAndSend(EXCHANGE_NAME, "fruit", msg);//交换机，routingKey，消息
          log.info("message : {}", msg);

     }
}
