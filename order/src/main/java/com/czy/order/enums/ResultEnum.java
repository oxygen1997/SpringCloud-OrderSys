package com.czy.order.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

     PARAM_ERROR(7, "参数错误"),
     CART_EMPTY(4, "购物车为空");

     private Integer code;

     private String message;

     ResultEnum(Integer code,String msg){
          this.code = code;
          this.message = message;
     }
}
