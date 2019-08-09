package com.czy.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
@Slf4j
public class RestClientController {

     @Autowired
     LoadBalancerClient loadBalancerClient;

//     @Autowired
//     RestTemplate restTemplate;

     @GetMapping("/msg")
     public String msg(){
          //RestTemplete调用服务第一种方式
//          RestTemplate restTemplate = new RestTemplate();
//          String result = restTemplate.getForObject("http://localhost:8001/server/msg", String.class);

          //第二种方式 注入依赖loadBalancerClient获得应用实例一次获得ip和端口
          RestTemplate restTemplate = new RestTemplate();
//          此处product全部大写
          ServiceInstance product = loadBalancerClient.choose("PRODUCT");
          String url = String.format("http://%s:%s", product.getHost(), product.getPort())+"/server/msg";
          log.info("【url】：{}",url);
          String result = restTemplate.getForObject(url, String.class);


          //第三种方式 编写RestTemplateConfig工具类，将创建的RestTemplate实例对象交给spring容器管理
//        String result = restTemplate.getForObject("http://PRODUCT/server/msg", String.class);
          log.info("【result】：{}", result);
          return result;
     }
}
