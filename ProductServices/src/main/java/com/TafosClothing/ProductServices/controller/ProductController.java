package com.TafosClothing.ProductServices.controller;


import com.TafosClothing.ProductServices.model.RequestProduct;
import com.TafosClothing.ProductServices.model.ResponseProduct;
import com.TafosClothing.ProductServices.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Product")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable("id") Long id){

        ResponseProduct responseProduct= productService.getProduct(id);

        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody RequestProduct requestProduct){

         Long id= productService.addProduct(requestProduct);

         return new ResponseEntity<>(id,HttpStatus.CREATED);
    }


    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void>  reduceQuantity(@PathVariable("id") Long ProductId,@RequestParam Long Quantity){

        productService.reduceQuantity(ProductId,Quantity);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
