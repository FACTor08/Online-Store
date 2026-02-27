package com.factor.shopOnline.Service;

import com.factor.shopOnline.Component.ProductDT;
import com.factor.shopOnline.Model.Products;
import com.factor.shopOnline.Model.ProductsDTO;
import com.factor.shopOnline.Repository.MainRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class MainLogic {
    @Autowired
    private  ProductDT productData;
    @Autowired
    private MainRepo repo;

    public String uploadItems(ProductsDTO product, MultipartFile photo) throws IOException {
      byte[] image = photo.getBytes();
      String imageType = photo.getContentType();

      Products data = productData.dataTransfer(product, image, imageType);

        repo.save(data);
            return "Product: " + product.getProduct() + " has been successfully stocked";
    }

    public List<Products> getProducts(){
        return repo.findAll();
    }

    public List<Products> getById(String cat , String prod){
     return repo.findByCategoryIgnoreCaseOrProductIgnoreCase(cat,prod);

    }

    public String updateProduct(String item, ProductsDTO product, MultipartFile image){
     Products key = repo.findByProductIgnoreCase(item)
             .orElseThrow(()-> new RuntimeException("Product not Found"));
        boolean updated = false;
                    if (product.getProduct() != null) {
                        key.setProduct(product.getProduct());
                        updated = true;
                    }
                    if (product.getPrice() != 0) {
                        key.setPrice(product.getPrice());
                        updated = true;
                    }
                    if (product.getAvailableUnits() != 0) {
                        key.setAvailableUnits(product.getAvailableUnits());
                        updated = true;
                    }
                    if (product.getDescription() != null) {
                        key.setDescription(product.getDescription());
                        updated = true;
                    }
                    if (product.getCategory() != null) {
                        key.setCategory(product.getCategory());
                        updated = true;
                    }
                    if (image != null && !image.isEmpty()) {
                        try {
                            key.setProductImage(image.getBytes());
                            updated = true;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    repo.save(key);
        return "Product data Updated Successfully";
    }



}


