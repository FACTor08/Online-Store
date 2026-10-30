package com.factor.shopOnline.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO {

    private String email;
    private String password;
    private String username;
    private String phone;
}
