package com.factor.shopOnline.merchant;

import com.factor.shopOnline.User.Roles;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MerchantResponseDTO {
    private long id;
    private String email;
        private String username;
        private Roles roles;
}
