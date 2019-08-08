package com.czy.product.dao;

import com.czy.product.dto.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
     /** hibernate 按照方法名去查找实体类中的属性，通过多个id查找结果findByCategoryIdIn，去掉in就是只查找一个，In是查找多个 */
    List<ProductCategory> findByCategoryIdIn(List<Integer> categoryTypeList);
}
