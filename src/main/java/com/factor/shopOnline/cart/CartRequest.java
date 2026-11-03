package com.factor.shopOnline.cart;

import lombok.Builder;

@Builder
public record CartRequest (
        String product,
        int quantity
){
}
