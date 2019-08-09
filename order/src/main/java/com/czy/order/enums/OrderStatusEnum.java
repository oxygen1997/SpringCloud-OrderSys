package com.czy.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

     NEW(6,"新订单"),
     FINISHED(3,"已支付"),
     CANCELED(0,"取消订单");

    private Integer code;

    private String msg;

     OrderStatusEnum(Integer code,String msg){
          this.code = code;
          this.msg = msg;
    }
}
