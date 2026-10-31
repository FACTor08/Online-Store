package com.factor.shopOnline.product;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductMapper {



    public Products dataTransfer(ProductsDTO data, Map<String, Object> image){
        Products entity = new Products();
        entity.setAvailableUnits(data.getAvailableUnits());
        entity.setCategory(data.getCategory());
        entity.setDescription(data.getDescription());
        entity.setPrice(data.getPrice());
        entity.setProduct(data.getProduct());


        if (image != null){
        entity.setProductImage((String)image.get("secure_url"));
        entity.setImageId((String) image.get("public_id"));
        }else
        {
            throw new IllegalArgumentException("image is null");
        }

        return entity;
    }
    public ProductResponseDTO getData(Products products){
        return ProductResponseDTO.builder()
                .id(products.getId())
                .product(products.getProduct())
                .category(products.getCategory())
                .description(products.getDescription())
                .price(products.getPrice())
                .availableUnits(products.getAvailableUnits())
                .image(products.getProductImage())
                .build();
    }
}
