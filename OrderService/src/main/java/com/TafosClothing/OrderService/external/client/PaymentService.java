package com.TafosClothing.OrderService.external.client;


import com.TafosClothing.OrderService.exception.CustomException;
import com.TafosClothing.OrderService.external.request.RequestPayment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CircuitBreaker(name = "external",fallbackMethod = "fallBack")
@FeignClient(name = "PAYMENT-SERVICE/Payment")
public interface PaymentService {

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody RequestPayment requestPayment);


    default void  fallBack(Exception e){
        throw  new CustomException("Payment service is unavailable","UNAVAILABLE",500);
    }
}
