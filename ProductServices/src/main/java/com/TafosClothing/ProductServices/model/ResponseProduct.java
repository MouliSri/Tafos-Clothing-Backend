package com.TafosClothing.ProductServices.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProduct {

    private Long id;

    private String name;

    private Long price;

    private Long Quantity;
}
