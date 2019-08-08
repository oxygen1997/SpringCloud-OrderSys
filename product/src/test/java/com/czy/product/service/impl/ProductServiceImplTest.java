package com.czy.product.service.impl;

import com.czy.product.ProductApplicationTests;
import com.czy.product.dto.ProductInfo;
import com.czy.product.enums.ProductStatusEnum;
import com.czy.product.service.ProductService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

     @Autowired
     ProductService productService;

     @Test
     public void findByProductStatus() {
          List<ProductInfo> productStatus = productService.findByProductUpAll();
          System.out.println(productStatus);
          Assert.assertTrue(productStatus.size()>0);
     }

     @Test
     public void findByProductIdIn() {
          List<ProductInfo> productIdIn = productService.findByProductIdIn(Arrays.asList("1001", "1002"));
          System.out.println(productIdIn);
          Assert.assertTrue(productIdIn.size()>0);
     }
}