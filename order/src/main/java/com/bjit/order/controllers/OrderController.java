package com.bjit.order.controllers;


import com.bjit.order.config.FeignBookConfig;

import com.bjit.order.config.FeignClientsConfig;
import com.bjit.order.model.Book;
import com.bjit.order.model.OrderRequest;
import com.bjit.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class OrderController {

    Logger logger = Logger.getLogger("HelloController");

    private final OrderService orderService;

    @Autowired
    FeignBookConfig feignBookConfig;

    @Autowired
    FeignClientsConfig feignClientsConfig;

    @PostMapping("/create-order")
    public ResponseEntity<Object> hello (@RequestBody OrderRequest orderRequest){

        System.out.println(orderRequest.getBook_Id());

        Integer bookId = orderRequest.getBook_Id();

        ResponseEntity<Book> bookResponse;
        Book book;

        try {
            bookResponse = feignBookConfig.getBookById(bookId);
            book = bookResponse.getBody();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Book not found!");
        }

        return orderService.createOrder(orderRequest);

    }

    @GetMapping("/all-order")
    public ResponseEntity<Object> getOrder (){
        return orderService.getAllOrder();
    }

    @GetMapping("/getPayment")
    public Boolean hello_o (){

        return  feignClientsConfig.getPayment();
    }
}
