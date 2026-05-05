package com.factor.shopOnline.mapper;

import com.factor.shopOnline.entity.ProductsDTO;
import com.factor.shopOnline.entity.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductDT {


    private ProductsDTO data;

    public Products dataTransfer(ProductsDTO data, byte[] image, String imageType){
        Products entity = new Products();

        entity.setAvailableUnits(data.getAvailableUnits());
        entity.setCategory(data.getCategory());
        entity.setDescription(data.getDescription());
        entity.setPrice(data.getPrice());
        entity.setProduct(data.getProduct());
        entity.setProductImage(image);
        entity.setImageType(imageType);
        return entity;
    }
}
