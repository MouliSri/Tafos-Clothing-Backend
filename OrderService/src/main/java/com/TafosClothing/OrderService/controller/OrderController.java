package com.TafosClothing.OrderService.controller;


import com.TafosClothing.OrderService.model.OrderRequest;
import com.TafosClothing.OrderService.model.OrderResponse;
import com.TafosClothing.OrderService.service.ServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderController {


    @Autowired
    ServiceOrder serviceOrder;


    @PostMapping("/PlaceOrder")
    public ResponseEntity<Long> PlaceOrder(@RequestBody OrderRequest orderRequest){

        Long OrderId=serviceOrder.PlaceOrder(orderRequest);
        return new ResponseEntity<>(OrderId, HttpStatus.OK);
    }


    @GetMapping("/{Id}")
    public  ResponseEntity<OrderResponse> GetOrderById(@PathVariable Long Id,@RequestHeader("LoggedUser") String user){

        System.out.println(user + " the user logged in ❤");

          return new ResponseEntity<>(serviceOrder.getOrderById(Id),HttpStatus.OK);

    }


}
