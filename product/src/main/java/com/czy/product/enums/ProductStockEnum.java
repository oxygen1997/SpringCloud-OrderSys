package com.czy.product.enums;

import lombok.Getter;

@Getter
public enum ProductStockEnum {
     STOCK_NOT_EXIST(0,"商品不存在"),
     PRODUCT_STOCK_ERROR(2, "库存不足"),
     ;
     private Integer code;
     private String message;
     ProductStockEnum(Integer code,String message){
          this.code = code;
          this.message = message;
     }
}
