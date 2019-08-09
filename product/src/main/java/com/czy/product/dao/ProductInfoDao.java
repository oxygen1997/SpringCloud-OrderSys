package com.czy.product.dao;

import com.czy.product.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

      List<ProductInfo> findByProductStatus(Integer productStatus);

     /** 加一个In表示在形参范围内的值 */
      List<ProductInfo> findByProductIdIn(List<String> productList);
}
