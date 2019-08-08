package com.czy.product.service;

import com.czy.product.dto.ProductCategory;

import java.util.List;

public interface CategoryService {
     List<ProductCategory> findByCategoryIdIn(List<Integer> categoryTypeList);
}
