package com.bjit.order.config;

import com.bjit.order.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="book-app-rest-api")
public interface FeignBookConfig {

    @GetMapping("/books/id/{bookId}")
    ResponseEntity<Book> getBookById(@PathVariable("bookId") Integer bookId);

}
