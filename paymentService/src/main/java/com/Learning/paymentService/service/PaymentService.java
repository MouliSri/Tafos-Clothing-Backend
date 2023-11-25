package com.Learning.paymentService.service;

import com.Learning.paymentService.model.RequestPayment;
import com.Learning.paymentService.model.ResponsePayment;

public interface PaymentService {
    Long doPayment(RequestPayment requestPayment);

    ResponsePayment GetPaymentDetailsByOrderId(Long orderID);
}
