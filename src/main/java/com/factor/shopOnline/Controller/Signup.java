package com.factor.shopOnline.Controller;

import com.factor.shopOnline.Model.ClientDTO;
import com.factor.shopOnline.Model.MerchantDTO;
import com.factor.shopOnline.Service.ClientLogic;
import com.factor.shopOnline.Service.MerchantLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Signup {

    @Autowired
    private MerchantLogic logic;

    @Autowired
    private ClientLogic clientLogic;

    @PostMapping("/MerchantSignUp")
    public ResponseEntity<String> merchantSignUp(@RequestBody(required = true) MerchantDTO data){
        String msg = logic.addAdmin(data);
        return ResponseEntity.status(200).body(msg);
    }
    @PostMapping("/SignUp")
    public ResponseEntity<String> clientSignUp(@RequestBody(required = true) ClientDTO data){
        String msg = clientLogic.clientSignUp(data);
        return ResponseEntity.status(200).body(msg);
    }
}
