package com.TafosClothing.OrderService.exception;



import com.TafosClothing.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse>  ProductExceptionHandler(CustomException exception){

        return new ResponseEntity<>(new ErrorResponse().builder().errorMessage(exception.getMessage()).errorCode(exception.getErrorCode()).build(), HttpStatusCode.valueOf(exception.getErrorStatus()));
    }
}
