package com.factor.shopOnline.Component;

import com.factor.shopOnline.Model.Merchant;
import com.factor.shopOnline.Model.MerchantDTO;
import com.factor.shopOnline.Model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MerchantDT {

    @Autowired
    private PasswordEncoder encoder;


    public Merchant dataTrnsf(MerchantDTO data){
        Merchant transfer = new Merchant();

        transfer.setEmail(data.getEmail());
        transfer.setPassword(encoder.encode(data.getPassword()));
        transfer.setUsername(data.getUsername());
        transfer.setPhone(data.getPhone());
        transfer.setRoles(Roles.ROLE_RETAILER);

        return transfer;
    }
}
