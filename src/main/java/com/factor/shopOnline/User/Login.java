package com.factor.shopOnline.User;

import com.factor.shopOnline.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Login {
    private final LoginLogic loginLogic;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginData) {
       UserData user = loginLogic.login(loginData);
    log.info("logging {} in", loginData.getUsername());
        return jwtUtil.generateToken(user);
    }
}
