package com.TafosClothing.OrderService.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomException  extends  RuntimeException{

   private String ErrorCode;
   private int ErrorStatus;

   public CustomException(String ErrorMessage, String ErrorCode, int ErrorStatus){
       super(ErrorMessage);
       this.ErrorCode=ErrorCode;
       this.ErrorStatus=ErrorStatus;
   }
}
