package com.factor.shopOnline.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartLogic cartLogic;

    @PostMapping("/fillCart")
    public ResponseEntity<CartResponse> fillCart(Authentication auth, @RequestBody CartRequest cartRequest) {
        String username = auth.getName();
        CartResponse response = cartLogic.addCart(username, cartRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getCart")
    public ResponseEntity<CartResponse> getCart(Authentication auth) {
        String username = auth.getName();
        CartResponse response = cartLogic.getCart(username);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("updateCart")
    public ResponseEntity<CartResponse> updateCart(Authentication auth, @RequestBody CartRequest cartRequest) {
        String username = auth.getName();
        CartResponse response = cartLogic.updateCart(username, cartRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/Remove")
    public ResponseEntity<CartResponse> removeFromCart(Authentication auth, @RequestBody CartRequest cartRequest) {
        String username = auth.getName();
        CartResponse response = cartLogic.deleteCart(username, cartRequest);
        return ResponseEntity.ok(response);
    }
}
