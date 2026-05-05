package com.factor.shopOnline.controller;

import com.factor.shopOnline.entity.Products;
import com.factor.shopOnline.service.MainLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Getmaps {

    @Autowired
    private MainLogic logic;

    @GetMapping("/store")
    public ResponseEntity<List<Products>> home(){
        List<Products> products = logic.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> search(@RequestParam(required = false) String q,
                                                 @RequestParam(required = false) String category){
        List<Products> results = logic.getById(q,category);

        return ResponseEntity.ok(results);
    }
}
