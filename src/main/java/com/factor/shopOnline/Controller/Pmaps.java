package com.factor.shopOnline.Controller;

import com.factor.shopOnline.Model.ClientDTO;
import com.factor.shopOnline.Model.MerchantDTO;
import com.factor.shopOnline.Model.ProductsDTO;
import com.factor.shopOnline.Service.ClientLogic;
import com.factor.shopOnline.Service.MainLogic;
import com.factor.shopOnline.Service.MerchantLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class Pmaps {
    @Autowired
    private MainLogic logic;
    @Autowired
    private ClientLogic logicB;
    @Autowired
    private MerchantLogic logicC;

    @PostMapping(value = "/addStock", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> stock(@ModelAttribute ProductsDTO data,
                                        @RequestPart(value = "photo", required = true) MultipartFile photo)
            throws IOException {

        String msg = logic.uploadItems(data, photo);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping(value = "/update/{item}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> stock(@PathVariable String item,
                                        @RequestPart(value = "data",required = false)ProductsDTO data,
                                        @RequestPart(value = "image", required = false) MultipartFile image)
            throws IOException {

        String msg = logic.updateProduct(item, data, image);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/update/client/{user}")
    public ResponseEntity<String> updateUserData(@PathVariable String user,
                                                 @RequestBody(required = false) ClientDTO client) {
        String msg = logicB.updateData(user, client);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/update/admin/{admin}")
    public ResponseEntity<String> updateAdminData(@PathVariable String admin,
                                                  @RequestBody( required = false) MerchantDTO merchant) {
        String msg = logicC.updateData(admin, merchant);
        return ResponseEntity.ok(msg);
    }
}
