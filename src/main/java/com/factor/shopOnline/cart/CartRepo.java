package com.factor.shopOnline.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
   Optional<Cart> findByUserAndProductId(String user, long id);

    List<Cart> findByUser(String user);
}
