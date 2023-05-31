package com.bjit.order.service.impl;

import com.bjit.order.entity.OrderEntity;
import com.bjit.order.model.OrderRequest;

import com.bjit.order.model.OrderResponseModel;
import com.bjit.order.repository.OrderRepository;
import com.bjit.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;


    @Override
    public ResponseEntity<Object> createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = OrderEntity.builder()
                .book_Id(orderRequest.getBook_Id())
                .bookName(orderRequest.getBookName())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getOrderPrice())
                .build();
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        OrderResponseModel orderResponse = OrderResponseModel.builder()
                .orderId(savedOrder.getOrderId())
                .bookId(savedOrder.getBook_Id())
                .orderQuantity(savedOrder.getQuantity())
                .build();
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllOrder() {
        List<OrderEntity> OrderList = orderRepository.findAll();
        List<OrderResponseModel> responseList = new ArrayList<>();
        for (OrderEntity order : OrderList) {
            OrderResponseModel orderResponseModel = OrderResponseModel.builder()
                    .orderId(order.getOrderId())
                    .bookId(order.getBook_Id())
                    .orderQuantity(order.getQuantity())
                    .build();
            responseList.add(orderResponseModel);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public ResponseEntity<Object> orderDetails(Long orderId) {
        return null;
    }


}
