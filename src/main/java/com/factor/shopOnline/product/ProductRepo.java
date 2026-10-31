package com.factor.shopOnline.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Products, Long> {

    Optional<Products> findByProductIgnoreCase(String product);


    Optional<Products> findByCategoryIgnoreCaseOrProductContainingIgnoreCase(String category, String product);
}
