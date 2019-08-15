package com.czy.product.service.impl;

import com.czy.product.dao.ProductInfoDao;
import com.czy.product.enums.ProductStockEnum;
import com.czy.product.pojo.DecreaseStockInput;
import com.czy.product.pojo.ProductInfo;
import com.czy.product.enums.ProductStatusEnum;
import com.czy.product.product.ProductException;
import com.czy.product.service.ProductService;
import com.czy.product.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

     @Autowired
     ProductInfoDao productInfoDao;

     @Autowired
     AmqpTemplate amqpTemplate;
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
     public void decreaseProductStock(List<DecreaseStockInput> decreaseStockInputList) {
          List<ProductInfo> productInfoList = decreaseProductStockProcess(decreaseStockInputList);
          //发送mq消息扣库存
          amqpTemplate.convertAndSend("Order","order", JsonUtil.toJson(productInfoList));
     }

     @Transactional
     public List<ProductInfo> decreaseProductStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
          List<ProductInfo> productInfoList = new ArrayList<>();
          for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
               Optional<ProductInfo> optionalProductInfo = productInfoDao.findById(decreaseStockInput.getProductId());
               //判断商品是否存在
               if(!optionalProductInfo.isPresent()){
                    throw new ProductException(ProductStockEnum.STOCK_NOT_EXIST);
               }
//             获得商品信息
               ProductInfo productInfo = optionalProductInfo.get();
//             减库存
               Integer result =  productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
//             当库存减完之后不足时抛出异常
               if (result < 0){
                    throw new ProductException(ProductStockEnum.PRODUCT_STOCK_ERROR);
               }
               //设置库存
               productInfo.setProductStock(result);
               //更新数据
               productInfoDao.save(productInfo);
               productInfoList.add(productInfo);
          }
          return productInfoList;
     }
}
