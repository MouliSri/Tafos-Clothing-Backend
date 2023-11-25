package com.TafosClothing.OrderService.service;

import com.TafosClothing.OrderService.model.OrderRequest;
import com.TafosClothing.OrderService.model.OrderResponse;

public interface ServiceOrder {
    Long PlaceOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(Long id);
}
