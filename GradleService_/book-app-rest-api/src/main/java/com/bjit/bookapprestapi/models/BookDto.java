package com.bjit.bookapprestapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String bookName;
    private String summary;
    private String authorName;
    private Integer quantity;
    private Integer price;
}