package com.factor.shopOnline.repository;

import com.factor.shopOnline.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepo extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByUsernameIgnoreCase(String username);

    Optional<Merchant> findByEmailIgnoreCase(String email);

    Optional<Merchant> findByPhone(String phone);
}
