package com.factor.shopOnline.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.factor.shopOnline.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class ProductService {
    private final ProductMapper productData;
    private final Cloudinary cloudinary;
    private final ProductRepo repo;

    public ProductResponseDTO uploadItems(ProductsDTO product, MultipartFile photo) throws IOException {
        log.info("Uploading new product: {} at {}", product.getProduct(), product.getPrice());
        if (photo == null || photo.isEmpty()) {
            log.warn("Image is empty");
            throw new ProductNotFoundException("no product found");

        }
        String contentType = photo.getContentType();
        String fileName = photo.getOriginalFilename();
        boolean validType = contentType != null && List.of("image/png","image.jpeg").contains(contentType);
        boolean jfifPermit = fileName != null && fileName.toLowerCase().endsWith(".jfif");
        if(!validType && !jfifPermit) {
            throw new IllegalArgumentException(photo.getContentType() + " is not supported");
        }

         Map<String, Object> image = null;
        log.info("Uploading {} image to Cloudinary", product.getProduct());
         image = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.asMap(
                 "resource_type", "image",
                 "folder", "products/image"
         ));
        Products data = productData.dataTransfer(product, image);
        Products save = repo.save(data);
        return productData.getData(save);
    }

    public List<ProductResponseDTO> getProducts(){
        List<Products> product =  repo.findAll();
        if (product.isEmpty()) {
            throw new ProductNotFoundException("no products available");
        }
        return product.stream()
                      .map(productData::getData)
                      .toList();
    }

    public ProductResponseDTO getByCategoryOrProduct(String cat , String prod){
        log.info("searching item by category: {} or product: {}", cat, prod);
     Products product = repo.findByCategoryIgnoreCaseOrProductContainingIgnoreCase(cat,prod)
             .orElseThrow(() -> new ProductNotFoundException("no product found"));

     return productData.getData(product);
    }

    public ProductResponseDTO updateProduct(String item, ProductsDTO product){
        log.info("updating {}", product.getProduct());

     Products key = repo.findByProductIgnoreCase(item)
             .orElseThrow(() -> new ProductNotFoundException("no product found"));
                    if (product.getPrice() != null) {
                        key.setPrice(product.getPrice());
                    }
                    if (product.getAvailableUnits() != null) {
                        key.setAvailableUnits(product.getAvailableUnits());
                    }
                    if (product.getCategory() != null) {
                        key.setCategory(product.getCategory());
                    }
                     log.info("update successful");
                     Products update = repo.save(key);
                    return productData.getData(update);
    }

}


