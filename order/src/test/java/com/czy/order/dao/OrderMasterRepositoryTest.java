package com.czy.order.dao;

import com.czy.order.pojo.OrderMaster;
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
public class OrderMasterRepositoryTest {

     @Autowired
     OrderMasterRepository orderMasterRepository;

     @Test
     public void testSave(){
          OrderMaster orderMaster = new OrderMaster();
          orderMaster.setOrderId("153326515");
          orderMaster.setBuyerName("吴彦祖");
          orderMaster.setBuyerPhone("188888888888");
          orderMaster.setBuyerAddress("阿富汗");
          orderMaster.setBuyerOpenid("156666835");
          orderMaster.setOrderAmount(new BigDecimal(12.03));
          orderMaster.setOrderStatus(0);
          orderMaster.setPayStatus(0);
          orderMaster.setCreateTime(new Date());
          orderMaster.setUpdateTime(new Date());
          OrderMaster save = orderMasterRepository.save(orderMaster);
          Assert.assertTrue(save!=null);

     }

}