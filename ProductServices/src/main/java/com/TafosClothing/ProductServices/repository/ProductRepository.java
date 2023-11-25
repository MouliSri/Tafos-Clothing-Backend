package com.TafosClothing.ProductServices.repository;

import com.TafosClothing.ProductServices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
}
