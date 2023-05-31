package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    Logger logger = Logger.getLogger("HelloController");

    @Autowired
    FeignBookConfig feignClientsConfig;

    @GetMapping("/hello")
    public String hello (){
        //logger.info("Hello");
        return "Hello service" + feignClientsConfig.getBookById(2);
    }
}
