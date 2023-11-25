package com.TafosClothing.ProductServices.exception;


import lombok.Data;

@Data
public class ProductServiceException extends  RuntimeException{

    private String errorCode;
    public ProductServiceException(String errorMessage ,String errorCode){


        super(errorMessage);

        this.errorCode=errorCode;

    }
}
