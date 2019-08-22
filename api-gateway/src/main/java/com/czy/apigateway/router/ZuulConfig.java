package com.czy.apigateway.router;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * 配置动态路由
 */
@Component
public class ZuulConfig {

     @RefreshScope
     @ConfigurationProperties("zuul")
     public ZuulProperties zuulProperties(){
          return new ZuulProperties();
     }
}
