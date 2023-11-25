package com.TafosClothing.OrderService.external.client;


import com.TafosClothing.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external",fallbackMethod = "fallBack")
@FeignClient(name = "PRODUCT-SERVICE/Product")
public interface ProductService {



    @PutMapping("/reduceQuantity/{id}")
     ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long ProductId, @RequestParam Long Quantity);


    default void  fallBack(Exception e){
        throw  new CustomException("Product service is unavailable","UNAVAILABLE",500);
    }
}
