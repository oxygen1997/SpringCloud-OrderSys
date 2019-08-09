package com.czy.order.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成唯一的订单id的工具类
 */
public class GenUniqueKeyUtil {

     /**
      * 生成唯一的订单Orderid
      * 时间戳+uuid
      * @return
      */
     public static synchronized String getUniqueKey(){

          Random random = new Random();
          Integer number = random.nextInt(900000) + 100000;
          return System.currentTimeMillis() + String.valueOf(number);

     }
}
