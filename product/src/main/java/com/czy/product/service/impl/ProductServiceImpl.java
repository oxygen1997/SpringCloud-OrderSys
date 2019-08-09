package com.czy.product.service.impl;

import com.czy.product.dao.ProductInfoDao;
import com.czy.product.enums.ProductStockEnum;
import com.czy.product.pojo.DecreaseStockInput;
import com.czy.product.pojo.ProductInfo;
import com.czy.product.enums.ProductStatusEnum;
import com.czy.product.product.ProductException;
import com.czy.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

     @Autowired
     ProductInfoDao productInfoDao;

     /**
      * 查询所有在架商品 product_status=0 在架，1为下架
      * @return
      */
     @Override
     public List<ProductInfo> findByProductUpAll() {
          return productInfoDao.findByProductStatus(ProductStatusEnum.up.getCode());
     }

     /**
      * 根据多个商品的id查询多个商品
      * @param productList 商品ID product_id
      * @return
      */
     @Override
     public List<ProductInfo> findList(List<String> productList) {
          return productInfoDao.findByProductIdIn(productList);
     }

     @Override
     public List<ProductInfo> decreaseProductStock(List<DecreaseStockInput> decreaseStockInputList) {
          List<ProductInfo> productInfoList = new ArrayList<>();
          for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
               Optional<ProductInfo> optionalProductInfo = productInfoDao.findById(decreaseStockInput.getProductId());
               if(!optionalProductInfo.isPresent()){
                    throw new ProductException(ProductStockEnum.STOCK_NOT_EXIST);
               }
               ProductInfo productInfo = optionalProductInfo.get();
               Integer result =  productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
               if (result < 0){
                    throw new ProductException(ProductStockEnum.PRODUCT_STOCK_ERROR);
               }
               productInfo.setProductStock(result);
               productInfoDao.save(productInfo);
               productInfoList.add(productInfo);
          }
          return productInfoList;
     }
}
