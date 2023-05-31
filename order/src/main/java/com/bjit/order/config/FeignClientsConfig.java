package com.bjit.order.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="payment-app")
public interface FeignClientsConfig {
    @GetMapping("/getPayment")
    Boolean getPayment();
}