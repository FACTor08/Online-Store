package com.factor.shopOnline.product;

import lombok.Data;
import lombok.Builder;

@Builder
@Data
public class ProductResponseDTO {

    private long id;
    private Integer availableUnits;
    private String category;
    private String description;
    private Double price;
    private String product;
    private String image;
}
