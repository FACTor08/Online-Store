package com.factor.shopOnline.Repository;

import com.factor.shopOnline.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainRepo extends JpaRepository<Products, Long> {

    Optional<Products> findByProductIgnoreCase(String product);


    List<Products> findByCategoryIgnoreCaseOrProductIgnoreCase(String category,String product);
}
