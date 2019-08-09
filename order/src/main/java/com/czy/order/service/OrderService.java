package com.czy.order.service;

import com.czy.order.dto.OrderDTO;

public interface OrderService {
     /**
      * 创建订单
      * @param orderDTO
      * @return
      */
     OrderDTO create(OrderDTO orderDTO);
}
