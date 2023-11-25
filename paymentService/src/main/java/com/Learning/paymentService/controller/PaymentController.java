package com.Learning.paymentService.controller;


import com.Learning.paymentService.model.RequestPayment;
import com.Learning.paymentService.model.ResponsePayment;
import com.Learning.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody RequestPayment requestPayment){
        return new ResponseEntity<>(paymentService.doPayment(requestPayment), HttpStatus.OK);
    }

    @GetMapping("/Order/{OrderId}")
    public  ResponseEntity<ResponsePayment>  GetPaymentDetailsByOrderId(@PathVariable("OrderId") Long OrderID){


        return new ResponseEntity<>(paymentService.GetPaymentDetailsByOrderId(OrderID),HttpStatus.OK);
    }
}
