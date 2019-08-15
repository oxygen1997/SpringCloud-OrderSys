package com.czy.order.message;

import com.czy.order.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class ProductInfoReceive {

     @Autowired
     StringRedisTemplate stringRedisTemplate;

     final private static String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

     @RabbitListener(bindings = @QueueBinding(value = @Queue("productInfo"),exchange=@Exchange("Order"),key = "order"))
     public void message(String message){
          ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) JsonUtil.fromJson(message, ArrayList.class);
          log.info("queue is 【{}】 message 【ProductInfo】 is 【{}】","productInfo",list);
          //将最新库存存储至Redis
          for (LinkedHashMap map : list) {
//               String类型， key：value   product_stock_productId （商品id）: productStock  （库存）
               stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,map.get("productId")),
                       String.valueOf(map.get("productStock")));
               log.info("【key】: {} , 【value】{}",String.format(PRODUCT_STOCK_TEMPLATE,map.get("productId")),
                       String.valueOf(map.get("productStock")));
          }
     }

}
