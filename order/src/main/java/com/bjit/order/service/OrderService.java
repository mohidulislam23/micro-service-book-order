package com.bjit.order.service;

import com.bjit.order.model.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ResponseEntity<Object> createOrder(OrderRequest orderRequestM);
    ResponseEntity<Object> getAllOrder();
    void deleteOrder(Long orderId);
    ResponseEntity<Object> orderDetails( Long orderId) ;
}
