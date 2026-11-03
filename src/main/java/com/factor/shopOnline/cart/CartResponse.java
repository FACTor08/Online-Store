package com.factor.shopOnline.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse (
        List<CartContent> items,
        double total
){
}
