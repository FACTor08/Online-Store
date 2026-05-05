package com.factor.shopOnline.mapper;


import com.factor.shopOnline.entity.Client;
import com.factor.shopOnline.entity.ClientDTO;
import com.factor.shopOnline.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ClientDT {

    @Autowired
    private  PasswordEncoder encoder;

    public Client dataTransfer(ClientDTO data) {

        Client transfer = new Client();
        transfer.setEmail(data.getEmail());
        transfer.setPassword(encoder.encode(data.getPassword()));
        transfer.setPhone(data.getPhone());
        transfer.setUsername(data.getUsername());
        transfer.setRoles(Roles.ROLE_CLIENT);
    return transfer;
    }
}
