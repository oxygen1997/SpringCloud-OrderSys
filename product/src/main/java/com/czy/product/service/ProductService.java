package com.czy.product.service;

import com.czy.product.dto.ProductInfo;

import java.util.List;

public interface ProductService {
     List<ProductInfo> findByProductUpAll();
     /** 加一个In表示在形参范围内的值 */
     List<ProductInfo> findByProductIdIn(List<String> productList);
}
