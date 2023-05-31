package com.bjit.order.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponseModel {
    private Long orderId;
    private Integer bookId;
    private Integer orderQuantity;
    private String orderPrice;


}
