package com.TafosClothing.ProductServices.service;

import com.TafosClothing.ProductServices.entity.Product;
import com.TafosClothing.ProductServices.model.RequestProduct;
import com.TafosClothing.ProductServices.model.ResponseProduct;
import com.TafosClothing.ProductServices.repository.ProductRepository;
import com.TafosClothing.ProductServices.exception.ProductServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProductServiceImpl  implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseProduct getProduct(Long id) {

        log.info("finding the product by id");
        Product product=productRepository.findById(id).orElseThrow(()->new ProductServiceException("Oops The Item Is Not Available","PRODUCT_NOT_FOUND"));
        ResponseProduct responseProduct=new ResponseProduct();
        BeanUtils.copyProperties(product,responseProduct);
        return responseProduct;
    }

    @Override
    public Long addProduct(RequestProduct requestProduct) {



       Product product= Product.builder().name(requestProduct.getName()).price(requestProduct.getPrice()).Quantity(requestProduct.getQuantity()).build();

        productRepository.save(product);
        log.info("Product added successfully");

        return product.getId() ;
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {

        log.info("reducing the quantity {} from id {}",quantity,productId);
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductServiceException("Product Not Found With the Given Id","PRODUCT_NOT_FOUND"));

        if (product.getQuantity()<quantity) {
            throw new ProductServiceException("The Product Quantity is less ","INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity()-quantity);

        productRepository.save(product);
        log.info("the product quantity updated successfully");
    }
}
