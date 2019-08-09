package com.czy.product.dao;

import com.czy.product.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

     @Autowired
     ProductInfoDao productInfoDao;

     /** 查询没有下架的商品 */
     @Test
     public void findByProductStatus() {
          List<ProductInfo> status = productInfoDao.findByProductStatus(0);
          System.out.println(status.toString());
     }

     @Test
     public void findByProductId() {
          List<ProductInfo> info = productInfoDao.findByProductIdIn(Arrays.asList("1001", "1002"));
          System.out.println(info);
     }
}