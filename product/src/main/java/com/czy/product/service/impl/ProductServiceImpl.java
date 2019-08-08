package com.czy.product.service.impl;

import com.czy.product.dao.ProductInfoDao;
import com.czy.product.dto.ProductInfo;
import com.czy.product.enums.ProductStatusEnum;
import com.czy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

     @Autowired
     ProductInfoDao productInfoDao;

     @Override
     public List<ProductInfo> findByProductUpAll() {
          return productInfoDao.findByProductStatus(ProductStatusEnum.up.getCode());
     }

     @Override
     public List<ProductInfo> findByProductIdIn(List<String> productList) {
          return productInfoDao.findByProductIdIn(productList);
     }
}
