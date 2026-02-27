package com.factor.shopOnline.Component;


import com.factor.shopOnline.Model.Client;
import com.factor.shopOnline.Model.ClientDTO;
import com.factor.shopOnline.Model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
