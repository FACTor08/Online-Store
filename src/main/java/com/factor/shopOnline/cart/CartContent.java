package com.factor.shopOnline.cart;

import lombok.Builder;

@Builder
public record CartContent (
        long id,
        String user,
        String product,
        Double price,
        int quantity,
        Double grandTotal
){
}
