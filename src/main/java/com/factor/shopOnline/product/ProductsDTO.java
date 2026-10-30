package com.factor.shopOnline.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductsDTO {
    private Integer availableUnits;
    private String category;
    private String description;
    private Double price;
    private String product;
}
