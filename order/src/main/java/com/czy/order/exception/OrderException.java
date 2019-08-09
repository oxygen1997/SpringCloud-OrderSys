package com.czy.order.exception;

import com.czy.order.enums.ResultEnum;

public class OrderException extends RuntimeException {

     private Integer code;

     public OrderException(Integer code,String msg){
          super(msg);
          this.code = code;
     }

     public OrderException(ResultEnum resultEnum){
          super(resultEnum.getMessage());
          this.code = resultEnum.getCode();
     }
}
