package com.czy.product.service;

import com.czy.product.pojo.DecreaseStockInput;
import com.czy.product.pojo.ProductInfo;

import java.util.List;

public interface ProductService {
     List<ProductInfo> findByProductUpAll();
     /** 加一个In表示在形参范围内的值 */
     List<ProductInfo> findList(List<String> productList);
     //扣库存的方法
     List<ProductInfo> decreaseProductStock(List<DecreaseStockInput> decreaseStockInputList);
}
