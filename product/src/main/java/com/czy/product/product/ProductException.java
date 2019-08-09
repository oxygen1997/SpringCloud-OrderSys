package com.czy.product.product;

import com.czy.product.enums.ProductStockEnum;

public class ProductException extends RuntimeException {
     private Integer code;

     public ProductException(String message, Integer code) {
          super(message);
          this.code = code;
     }
     public ProductException(ProductStockEnum productStockEnum){
          super(productStockEnum.getMessage());
          this.code = productStockEnum.getCode();
     }

}
