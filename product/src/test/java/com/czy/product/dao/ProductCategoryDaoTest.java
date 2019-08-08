package com.czy.product.dao;

import com.czy.product.dto.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryDaoTest {

     @Autowired
     ProductCategoryDao productCategoryDao;
     @Test
     public void findByCategoryIdIn() {
          List<ProductCategory> CategoryIdIn = productCategoryDao.findByCategoryIdIn(Arrays.asList(1, 2));
          System.out.println(CategoryIdIn);
     }
}