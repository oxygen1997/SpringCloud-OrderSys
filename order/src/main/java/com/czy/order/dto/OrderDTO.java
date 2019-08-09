package com.czy.order.dto;

import com.czy.order.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

     private String orderId;

     private String buyerName;

     private String buyerPhone;

     private String buyerAddress;

     private String buyerOpenid;

     private BigDecimal orderAmount;

     private Integer orderStatus;

     private Integer payStatus;

//   OrderMaster与OrderDetail是一对多的关系，使用List对应多个OrderDetail

     private List<OrderDetail> orderDetailList;
}
