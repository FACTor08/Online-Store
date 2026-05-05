package com.factor.shopOnline.repository;

import com.factor.shopOnline.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByUsernameIgnoreCase(String Username);

    Optional<Client> findByEmailIgnoreCase(String email);

    Optional<Client> findByPhone(String phone);


}
