package com.factor.shopOnline.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
   Optional<Cart> findByUserAndProductId(String user, long id);

    Optional<Cart> findByUser(String user);
}
