package com.czy.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
//@Lazy(false)
public class MQReceiver {


     @RabbitListener(bindings = @QueueBinding(key= "computer",value=@Queue("computer-queue"), exchange=@Exchange("OrderEx")))
     public void mqComputerReceive(String message) {
          log.info("computer message is : {}",message);
          System.out.println("【computer message】 "+message);
     }

     @RabbitListener(bindings = @QueueBinding(key= "fruit",value=@Queue("fruit-queue"), exchange=@Exchange("OrderEx")))
     public void mqReceive(String message) {
          log.info("fruit message is : {}",message);
          System.out.println("【fruit message】 "+message);
     }
}
