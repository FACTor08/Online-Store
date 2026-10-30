package com.factor.shopOnline.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ProductController {
    private final ProductService logic;

    @PostMapping(value = "/addStock", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> newStock(@ModelAttribute ProductsDTO data,
                                                    @RequestPart(value = "photo") MultipartFile photo)
            throws IOException {
        log.info("adding {} at: {}", data.getProduct(), data.getPrice());
        ProductResponseDTO stock = logic.uploadItems(data, photo);
        return ResponseEntity.ok(stock);
    }

    @PatchMapping(value ="/update-product")
    public ResponseEntity<ProductResponseDTO> stock(@RequestParam String item,
                                                    @RequestBody ProductsDTO data)
    {
        log.info("updating {}", data.getProduct());
        ProductResponseDTO update = logic.updateProduct(item, data);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/store")
    public ResponseEntity<List<ProductResponseDTO>> home(){
        List<ProductResponseDTO> products = logic.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/store/search")
    public ResponseEntity<ProductResponseDTO> search(@RequestParam(required = false) String category,
                                                     @RequestParam(required = false) String product){
        ProductResponseDTO results = logic.getByCategoryOrProduct(category,product);
        return ResponseEntity.ok(results);
    }
}
