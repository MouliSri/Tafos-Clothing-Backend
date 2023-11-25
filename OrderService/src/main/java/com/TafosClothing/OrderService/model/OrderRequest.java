package com.TafosClothing.OrderService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Long productId;

    private Long totalAmount;

    private Long quantity;

    private PaymentMode payment;

}
