package com.factor.shopOnline.merchant;

import com.factor.shopOnline.User.UserData;
import com.factor.shopOnline.User.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MerchantMapper {


    private final PasswordEncoder encoder;


    public UserData dataTrnsf(MerchantDTO data){
        UserData transfer = new UserData();

        transfer.setOtherName(data.getOtherName());
        transfer.setUsername(data.getUsername());
        transfer.setRoles(Roles.ROLE_RETAILER);

        if(data.getFirstname().isEmpty() || data.getLastname().isEmpty()){
            throw new IllegalArgumentException("Firstname & Lastname are Required!!!");
        }
        transfer.setFirstname(data.getFirstname());
        transfer.setLastname(data.getLastname());

        if (data.getPhone().isEmpty()){
            throw new IllegalArgumentException("PhoneNumber is Required!!!");
        }
        transfer.setPhone(data.getPhone());

        if(data.getUsername().isEmpty()){
            transfer.setUsername(data.getFirstname());
        }

        if (data.getEmail().isEmpty() || data.getPassword().isEmpty()){
            throw new IllegalArgumentException("Email or Password is Required!!!");
        }
        transfer.setEmail(data.getEmail());
        transfer.setPassword(encoder.encode(data.getPassword()));
        return transfer;
    }
    public MerchantResponseDTO response(UserData data){
        return MerchantResponseDTO.builder()
                .id(data.getId())
                .username(data.getUsername())
                .email(data.getEmail())
                .roles(data.getRoles())
                .build();
    }
}
