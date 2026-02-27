package com.factor.shopOnline.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class MerchantDTO {
    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min=8, message = "Password cannot be less than 6 Characters")
    private String password;

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Phone number is required")
    private String phone;
}
