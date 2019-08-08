package com.czy.product.service.impl;

import com.czy.product.ProductApplicationTests;
import com.czy.product.dao.ProductCategoryDao;
import com.czy.product.dto.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceImplTest extends ProductApplicationTests {

     @Autowired
     ProductCategoryDao productCategoryDao;

     @Test
     public void findByCategoryIdIn() {
          List<ProductCategory> categoryIdIn = productCategoryDao.findByCategoryIdIn(Arrays.asList(1, 2));
          Assert.assertTrue(categoryIdIn.size()>0);
     }
}