package com.factor.shopOnline.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductsDTO {
    private int availableUnits;
    private String category;
    private String description;
    private double price;
    private String product;
}
