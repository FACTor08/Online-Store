package com.factor.shopOnline.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsernameIgnoreCase(String Username);

    Optional<UserData> findByEmailIgnoreCase(String email);

    Optional<UserData> findByPhone(String phone);

    Optional<UserData> findByUsername(String username);
}
