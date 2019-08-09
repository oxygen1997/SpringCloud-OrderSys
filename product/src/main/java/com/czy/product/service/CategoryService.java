package com.czy.product.service;

import com.czy.product.pojo.ProductCategory;

import java.util.List;

public interface CategoryService {
     List<ProductCategory> findByCategoryIdIn(List<Integer> categoryTypeList);
}
