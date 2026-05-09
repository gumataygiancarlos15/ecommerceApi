package com.ws101.gumatay.repository;

import com.ws101.gumatay.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method Naming
    List<Product> findByCategoryName(String name);

    // @Query with JPQL
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceRange(@Param("min") double min, @Param("max") double max);

    List<Product> findByNameContainingIgnoreCase(String name);
}