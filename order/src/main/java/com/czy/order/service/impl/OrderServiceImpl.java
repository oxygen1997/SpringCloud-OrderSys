package com.czy.order.service.impl;

import com.czy.order.client.ProductClient;
import com.czy.order.dao.OrderDetailRepository;
import com.czy.order.dao.OrderMasterRepository;
import com.czy.order.dto.CarDTO;
import com.czy.order.dto.OrderDTO;
import com.czy.order.enums.OrderStatusEnum;
import com.czy.order.enums.PayStatusEnum;
import com.czy.order.pojo.OrderDetail;
import com.czy.order.pojo.OrderMaster;
import com.czy.order.pojo.ProductInfo;
import com.czy.order.service.OrderService;
import com.czy.order.util.GenUniqueKeyUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

     @Autowired
     OrderMasterRepository orderMasterRepository;

     @Autowired
     OrderDetailRepository orderDetailRepository;

     @Autowired
     ProductClient productClient;


     @Override
     @Transactional
     public OrderDTO create(OrderDTO orderDTO) {
          //生成唯一id设置orderid
          String orderId = GenUniqueKeyUtil.getUniqueKey();
          // 查询商品信息（调用商品服务)
          //获得所有订单的items中商品id集合，并添加到orderIdList中
          List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
          List<String> productIdList = new ArrayList<>();
          for (OrderDetail orderDetail :orderDetailList){
               productIdList.add(orderDetail.getProductId());
          }
          List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

          // 计算总价  单价*数量
          BigDecimal orderAmount = new BigDecimal("0");
          for (OrderDetail orderDetail : orderDetailList){
               for (ProductInfo productInfo : productInfoList){
                    if(productInfo.getProductId().equals(orderDetail.getProductId())){
                         orderAmount =  productInfo.getProductPrice()
                                 .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                 .add(orderAmount);
                         //订单详情入库
                         orderDetail.setProductId(productInfo.getProductId());
                         orderDetail.setProductName(productInfo.getProductName());
                         orderDetail.setProductPrice(productInfo.getProductPrice());
                         orderDetail.setDetailId(GenUniqueKeyUtil.getUniqueKey());
                         orderDetail.setOrderId(orderId);
                         orderDetailRepository.save(orderDetail);
                    }
               }
          }

          // 扣库存（调用商品服务）
          CarDTO carDTO = new CarDTO();
          List<CarDTO> carDTOs = new ArrayList<>();
          for (OrderDetail orderDetail : orderDetailList){
               String productId = orderDetail.getProductId();
               Integer productQuantity = orderDetail.getProductQuantity();
               carDTO.setProductId(productId);
               carDTO.setProductQuantity(productQuantity);
               carDTOs.add(carDTO);
          }

          productClient.decreaseProduct(carDTOs);

          //订单主体入库
          OrderMaster orderMaster = new OrderMaster();
          orderDTO.setOrderId(orderId);
          BeanUtils.copyProperties(orderDTO,orderMaster);
          orderMaster.setOrderAmount(orderAmount);
          orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
          orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
          orderMasterRepository.save(orderMaster);
          return orderDTO;
     }
}
