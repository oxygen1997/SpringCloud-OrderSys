package com.czy.product.service.impl;

import com.czy.product.dao.ProductCategoryDao;
import com.czy.product.dto.ProductCategory;
import com.czy.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

     @Autowired
     ProductCategoryDao productCategoryDao;

     @Override
     public List<ProductCategory> findByCategoryIdIn(List<Integer> categoryTypeList) {
          return productCategoryDao.findByCategoryIdIn(categoryTypeList);
     }
}
