package com.czy.order.client;

import com.czy.order.dto.CarDTO;
import com.czy.order.pojo.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("PRODUCT")
public interface ProductClient {

     @GetMapping("/server/msg")
     String productMsg();

     @GetMapping("/product/getForOrderList")
     List<ProductInfo> listForOrder(List<String> productIdList);

     @GetMapping("/product/decreaseProductStock")
     List<ProductInfo> decreaseProduct(List<CarDTO> carDTOS);
}
