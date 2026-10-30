package com.factor.shopOnline.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String firstname;
    private String otherName;
    private String lastname;
    private String username;
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
