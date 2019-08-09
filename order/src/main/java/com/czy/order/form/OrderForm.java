package com.czy.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 前端表单信息
 */
@Data
public class OrderForm {

     /**
      * 买家姓名
      */
     @NotEmpty(message = "买家姓名不能为空")
     private String name;

     /**
      * 买家电话
      */
     @NotEmpty(message = "买家电话不能为空")
     private String phone;

     /**
      * 买家地址
      */
     @NotEmpty(message = "买家地址不能为空")
     private String address;

     /**
      * 微信openid
      */
     @NotEmpty(message = "微信openid不能为空")
     private String openid;

     /**
      * 购物车信息
      */
     @NotEmpty(message = "购物车信息不能为空")
     private String items;

}
