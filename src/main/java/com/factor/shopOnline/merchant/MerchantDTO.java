package com.factor.shopOnline.merchant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class MerchantDTO {

    private String email;
    private String password;
    private String username;
    private String firstname;
    private String otherName;
    private String lastname;
    private String phone;
}
