package com.bjit.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Book {

    private String bookName;
    private String summary;
    private String authorName;
    private Integer quantity;
    private Integer price;
}