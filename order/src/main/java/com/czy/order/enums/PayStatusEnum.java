package com.czy.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

     WAIT(1,"等待支付"),
     SUCCESS(2,"支付成功"),;

     private Integer code;
     private String msg;

     PayStatusEnum(Integer code,String msg){
          this.code = code;
          this.msg = msg;
     }
}
