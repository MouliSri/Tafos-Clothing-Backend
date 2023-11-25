package com.Learning.paymentService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePayment {

    private Long paymentId;

    private String Status;

    private  PaymentMode paymentMode;

    private String amount;

    private Instant Date;

}
