package com.TafosClothing.ProductServices.exception;

import com.TafosClothing.ProductServices.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorResponse>  ProductExceptionHandler(ProductServiceException exception){

        return new ResponseEntity<>(new ErrorResponse().builder().errorMessage(exception.getMessage()).errorCode(exception.getErrorCode()).build(), HttpStatus.NOT_FOUND);
    }
}
