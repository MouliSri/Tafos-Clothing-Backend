package com.Learning.paymentService.service;


import com.Learning.paymentService.entity.Payment;
import com.Learning.paymentService.model.PaymentMode;
import com.Learning.paymentService.model.RequestPayment;
import com.Learning.paymentService.model.ResponsePayment;
import com.Learning.paymentService.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements  PaymentService{

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public Long doPayment(RequestPayment requestPayment) {

        Payment payment=Payment.builder().amount(requestPayment.getAmount()).paymentMode(requestPayment.getPaymentMode().name()).paymentDate(Instant.now()).paymentStatus("SUCCESS").orderId(requestPayment.getOrderId()).referenceNumber(requestPayment.getReferenceNumber()).build();

        transactionRepository.save(payment);

        return payment.getId();
    }

    @Override
    public ResponsePayment GetPaymentDetailsByOrderId(Long orderID) {

        Payment payment=transactionRepository.findByorderId(orderID);

        if (payment==null) {
            return new ResponsePayment();
        }

        return  ResponsePayment.builder().paymentId(payment.getId()).paymentMode(PaymentMode.valueOf(payment.getPaymentMode())).amount(payment.getAmount()).Date(payment.getPaymentDate()).Status(payment.getPaymentStatus()).build();
    }
}
