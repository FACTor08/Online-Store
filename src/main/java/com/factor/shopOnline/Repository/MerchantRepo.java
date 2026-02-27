package com.factor.shopOnline.Repository;

import com.factor.shopOnline.Model.Merchant;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepo extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByUsernameIgnoreCase(String username);

    Optional<Merchant> findByEmailIgnoreCase(String email);

    Optional<Merchant> findByPhone(String phone);
}
