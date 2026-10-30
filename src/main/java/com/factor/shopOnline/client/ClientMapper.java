package com.factor.shopOnline.client;


import com.factor.shopOnline.User.UserData;
import com.factor.shopOnline.User.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final PasswordEncoder encoder;

    public UserData dataTransfer(ClientDTO clientData) {

        UserData clientMap = new UserData();
        clientMap.setUsername(clientData.getUsername());
        clientMap.setRoles(Roles.ROLE_CLIENT);

        if (clientData.getPhone().isEmpty()){
            throw new IllegalArgumentException("PhoneNumber is Required!!!");
        }else {
            clientMap.setPhone(clientData.getPhone());
        }
        if (clientData.getEmail().isEmpty() || clientData.getPassword().isEmpty()){
            throw new IllegalArgumentException("Email or Password is Required!!!");
        }else {
            clientMap.setEmail(clientData.getEmail());
            clientMap.setPassword(encoder.encode(clientData.getPassword()));
        }

    return clientMap;
    }
    public ClientResponseDTO response(UserData clientData) {
        return ClientResponseDTO.builder()
                .id(clientData.getId())
                .username(clientData.getUsername())
                .email(clientData.getEmail())
                .role(clientData.getRoles())
                .build();
    }
}
