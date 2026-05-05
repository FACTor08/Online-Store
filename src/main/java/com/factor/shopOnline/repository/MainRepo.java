package com.factor.shopOnline.repository;

import com.factor.shopOnline.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainRepo extends JpaRepository<Products, Long> {

    Optional<Products> findByProductIgnoreCase(String product);


    List<Products> findByCategoryIgnoreCaseOrProductIgnoreCase(String category,String product);
}
