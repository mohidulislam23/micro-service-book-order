package com.bjit.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    Integer book_Id;
    String bookName;
    Integer quantity;
    Integer orderPrice;
}
