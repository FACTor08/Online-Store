package com.factor.shopOnline.config;

import com.factor.shopOnline.User.UserData;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${JWT_SECRET_KEY}")
    private String secretCode;

private SecretKey secretKey(){
byte[] keyBytes = Decoders.BASE64.decode(secretCode);
    return Keys.hmacShaKeyFor(keyBytes);
}
public String generateToken(UserData user){
    return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
            .signWith(secretKey())
            .compact();
}

    public String extractToken(String token) {
     return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token) {
  try{
      Jwts.parser()
              .verifyWith(secretKey())
              .build()
              .parseSignedClaims(token);
        return true;
  } catch (JwtException e) {
      return false;
  }
}
}
