package com.czy.order.controller;

import com.czy.order.client.ProductClient;
import com.czy.order.dto.CarDTO;
import com.czy.order.pojo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/feign")
public class FeignClientController {

     @Autowired
     ProductClient productClient;

     //测试
     @GetMapping("/msg")
     public String msg(){
          return productClient.productMsg();
     }

     /**
      * 查询订单中商品列表
      * @return
      */
     @GetMapping("/getForOrderList")
     public List<ProductInfo> getList(){
          List<ProductInfo> list = productClient.listForOrder(Arrays.asList("1001","1002"));
          return list;
     }

     /**
      * 扣除库存
      * @return
      */
     @GetMapping("/decreaseProduct")
     public List<ProductInfo> decreaseProductStock(List<CarDTO> carDTOS){
          List<ProductInfo> productInfos = productClient.decreaseProduct(carDTOS);
          return productInfos;
     }
}
