package com.TafosClothing.ProductServices.service;

import com.TafosClothing.ProductServices.model.RequestProduct;
import com.TafosClothing.ProductServices.model.ResponseProduct;

public interface ProductService {
    ResponseProduct getProduct(Long id);

    Long addProduct(RequestProduct requestProduct);

    void reduceQuantity(Long productId, Long quantity);
}
