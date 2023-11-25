package com.Learning.paymentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;



@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;

    private String paymentMode;

    private String paymentStatus;

    private  String referenceNumber;

    private  String amount;
    private Instant paymentDate;
}
