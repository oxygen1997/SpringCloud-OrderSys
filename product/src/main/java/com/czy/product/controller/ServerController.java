package com.czy.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
@Slf4j
public class ServerController {
     private static Integer count = 1;
     @GetMapping("/msg")
     public String msg(){
          log.info("第 "+(count++)+" 次调用");
          return "this is product msg 2! ";
     }
}
