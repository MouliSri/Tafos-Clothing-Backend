package com.TafosClothing.OrderService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long OrderId;

    private String OrderStatus;

    private Instant OrderDate;

    private Long Amount;


    private  ProductDetails productDetails;

    private  PaymentDetails paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails {

        private Long id;

        private String name;

        private Long price;

        private Long Quantity;
    }


    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentDetails {

        private Long paymentId;

        private String Status;

        private PaymentMode paymentMode;

        private String amount;

        private Instant Date;

    }


}
