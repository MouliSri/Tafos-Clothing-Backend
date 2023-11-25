package com.TafosClothing.OrderService.external.request;


import com.TafosClothing.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPayment {
    private Long orderId;

    private  String referenceNumber;

    private String amount;


    private PaymentMode paymentMode;

}
