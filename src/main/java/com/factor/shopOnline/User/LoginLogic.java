package com.factor.shopOnline.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginLogic {
    private final UserRepo repo;
    private final PasswordEncoder encoder;

    public UserData login(LoginDTO request) {
        log.info("verifying {} before logging in", request.getUsername());
        UserData user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("wrong Username/password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("wrong Username/password");
        }else {
            return user;
        }
    }
}
