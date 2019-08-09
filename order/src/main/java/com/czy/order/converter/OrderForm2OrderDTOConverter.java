package com.czy.order.converter;

import com.czy.order.dto.OrderDTO;
import com.czy.order.enums.ResultEnum;
import com.czy.order.exception.OrderException;
import com.czy.order.form.OrderForm;
import com.czy.order.pojo.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

     public static OrderDTO converter(OrderForm orderForm){

          Gson gson = new Gson();
          OrderDTO orderDTO = new OrderDTO();
          orderDTO.setBuyerName(orderForm.getName());
          orderDTO.setBuyerPhone(orderForm.getPhone());
          orderDTO.setBuyerAddress(orderForm.getAddress());
          orderDTO.setBuyerOpenid(orderForm.getOpenid());
          List<OrderDetail> orderDetailList = new ArrayList<>();

          try{
              orderDetailList =  gson.fromJson(orderForm.getItems(),
                       new TypeToken<List<OrderDetail>>(){
                       }.getType());
          }
          catch (Exception e){
               log.error("[json转换]错误，string={}",orderForm.getItems());
               throw new OrderException(ResultEnum.PARAM_ERROR);
          }

          orderDTO.setOrderDetailList(orderDetailList);

          return orderDTO;
     }
}
