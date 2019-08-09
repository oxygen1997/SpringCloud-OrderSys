package com.czy.order.dao;

import com.czy.order.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

     @Autowired
     OrderDetailRepository orderDetailRepository;

     @Test
     public void save() {
          OrderDetail od = new OrderDetail();
          od.setDetailId("0");
          od.setOrderId("153326515");
          od.setProductId("1001");
          od.setProductName("八宝粥");
          od.setProductIcon("http://xxx.com");
          od.setProductPrice(new BigDecimal("15.05"));
          od.setProductQuantity(3);
          OrderDetail status = orderDetailRepository.save(od);
          Assert.assertTrue(status!=null);
     }
}