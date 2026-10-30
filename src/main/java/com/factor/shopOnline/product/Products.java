package com.factor.shopOnline.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productImage;
    private String imageId;
    private Integer availableUnits;
    private String category;
    private String description;
    private Double price;
    private String product;
}
