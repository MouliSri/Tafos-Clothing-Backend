package com.TafosClothing.OrderService.service;


import com.TafosClothing.OrderService.entity.Order;
import com.TafosClothing.OrderService.exception.CustomException;
import com.TafosClothing.OrderService.external.client.PaymentService;
import com.TafosClothing.OrderService.external.client.ProductService;
import com.TafosClothing.OrderService.external.request.RequestPayment;
import com.TafosClothing.OrderService.external.response.ResponsePayment;
import com.TafosClothing.OrderService.external.response.ResponseProduct;
import com.TafosClothing.OrderService.model.OrderRequest;
import com.TafosClothing.OrderService.model.OrderResponse;
import com.TafosClothing.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Slf4j
public class ServiceOrderImpl implements ServiceOrder{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PaymentService paymentService;


    @Autowired
    RestTemplate restTemplate;

    @Override
    public Long PlaceOrder(OrderRequest orderRequest) {

        log.info("checking the quantity and reduce it");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("placing an order");
        Order order= Order.builder().productId(orderRequest.getProductId()).date(Instant.now()).price(orderRequest.getTotalAmount()).quantity(orderRequest.getQuantity()).status("Order Placed").build();



        orderRepository.save(order);
        log.info("order placed successfully with Id : "+order.getOrderId() );


        RequestPayment requestPayment= RequestPayment.builder().paymentMode(orderRequest.getPayment()).orderId(order.getOrderId()).amount(String.valueOf(orderRequest.getTotalAmount())).build();

        String status=null;


        try{

            paymentService.doPayment(requestPayment);
            status="PLACED";
        }
        catch (Exception exception){

         status="PAYMENT_FAILED";
        }

        order.setStatus(status);

        orderRepository.save(order);

        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderById(Long id) {


        log.info("Getting the product by the given id :" + id);

        Order order=orderRepository.findById(id).orElseThrow(()-> new CustomException("Product Not Found With the Given Id",404));


        log.info("Getting the product Details");
         ResponseProduct responseProduct=restTemplate.getForObject("http://PRODUCT-SERVICE/Product/"+order.getProductId(), ResponseProduct.class);


         log.info("Geting the payment Details");
         ResponsePayment responsePayment=restTemplate.getForObject("http://PAYMENT-SERVICE/Payment/Order/"+ id, ResponsePayment.class);



         OrderResponse.ProductDetails productD=OrderResponse.ProductDetails.builder().name(responseProduct.getName()).id(responseProduct.getId()).Quantity(responseProduct.getQuantity()).price(responseProduct.getPrice()).build();


        OrderResponse.PaymentDetails paymentDetails=OrderResponse.PaymentDetails.builder().paymentId(responsePayment.getPaymentId()).paymentMode(responsePayment.getPaymentMode()).amount(responsePayment.getAmount()).Date(responsePayment.getDate()).Status(responsePayment.getStatus()).build();

        return OrderResponse.builder().OrderDate(order.getDate()).OrderStatus(order.getStatus()).OrderId(order.getOrderId()).Amount(order.getPrice()).productDetails(productD).paymentDetails(paymentDetails).build();
    }
}
