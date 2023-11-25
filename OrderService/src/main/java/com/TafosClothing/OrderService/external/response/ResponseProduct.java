package com.TafosClothing.OrderService.external.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
