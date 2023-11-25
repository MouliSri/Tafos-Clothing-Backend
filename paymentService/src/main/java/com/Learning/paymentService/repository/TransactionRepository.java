package com.Learning.paymentService.repository;


import com.Learning.paymentService.entity.Payment;
import com.Learning.paymentService.model.ResponsePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TransactionRepository   extends JpaRepository<Payment,Long> {






     Payment findByorderId(long orderID);
}
