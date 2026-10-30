package com.factor.shopOnline.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientLogic clientLogic;

    @PostMapping("/SignUp")
    public ResponseEntity<ClientResponseDTO> clientSignUp(@RequestBody ClientDTO data){
        ClientResponseDTO response = clientLogic.clientSignUp(data);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/client/update/user_data")
    public ResponseEntity<ClientResponseDTO> updateUserData(Authentication authentication,
                                                   @RequestBody(required = false) ClientDTO client) {
        String user = authentication.getName();
        return ResponseEntity.ok(clientLogic.updateData(user, client));
    }

    @PatchMapping("/client/update/user_handle")
    public ResponseEntity<ClientResponseDTO> updateUserName(Authentication authentication,
                                                   @RequestBody(required = false) ClientDTO client) {
        String username = authentication.getName();
        return ResponseEntity.ok(clientLogic.updateUsername(username, client));
    }
}
