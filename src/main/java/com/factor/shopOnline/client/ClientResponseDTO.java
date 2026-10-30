package com.factor.shopOnline.client;

import com.factor.shopOnline.User.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientResponseDTO {
    private long id;
    private String email;
    private Roles role;
    private String username;
}
