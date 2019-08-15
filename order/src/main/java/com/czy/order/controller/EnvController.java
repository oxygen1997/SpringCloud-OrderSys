package com.czy.order.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class EnvController {

     @Value("${mq.queue-name}")//此处的env引用的是配置文件里的env属性值
     private String x;

     @GetMapping("/print")
     public String print(){
          return x;
     }
}
