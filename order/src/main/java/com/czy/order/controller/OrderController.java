package com.czy.order.controller;

import com.czy.order.converter.OrderForm2OrderDTOConverter;
import com.czy.order.dto.OrderDTO;
import com.czy.order.enums.ResultEnum;
import com.czy.order.exception.OrderException;
import com.czy.order.form.OrderForm;
import com.czy.order.service.OrderService;
import com.czy.order.util.ResultVOUtil;
import com.czy.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
     /**
      * 第一步在controller，第2至第5在service处理
      * 1.参数校验
      * 2.查询商品信息（调用商品服务）
      * 3.计算总价
      * 4.扣库存（调用商品服务）
      * 5.订单入库
      */

     @Autowired
     OrderService orderService;

     @GetMapping("/create")
     public ResultVO<Map<String,String>>  create(@Valid OrderForm orderForm, BindingResult bindingResult){
          //判断表单参数是否正确
          if(bindingResult.hasErrors()){
               log.error("[创建订单]参数不正确,orderForm={}",orderForm);
               new Error();
               throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
          }
          //表单参数正确，将表单数据转换为OrderDTO数据传输对象
          OrderDTO result = OrderForm2OrderDTOConverter.converter(orderForm);
          //判断DTO中的OrderDetailList是否为空
          if(CollectionUtils.isEmpty(result.getOrderDetailList())){
               log.error("【创建订单】购物车信息为空");
               throw new OrderException(ResultEnum.CART_EMPTY);
          }
          OrderDTO orderDTO = orderService.create(result);
          Map<String,String> map = new HashMap<>();
          map.put("orderId",result.getOrderId());
          return ResultVOUtil.success(map);
     }


}
