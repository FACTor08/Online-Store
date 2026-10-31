package com.factor.shopOnline.merchant;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MerchantController {
    private final MerchantLogic merchantLogic;

    @PostMapping("/MerchantSignUp")
    public ResponseEntity<MerchantResponseDTO> merchantSignUp(@RequestBody MerchantDTO data){
        log.info("signing in new Merchant: {}", data.getUsername());
        MerchantResponseDTO response = merchantLogic.addAdmin(data);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("admin/update")
    public ResponseEntity<MerchantResponseDTO> updateAdminData(Authentication authenticate,
                                                    @RequestBody( required = false) MerchantDTO merchant) {
    log.info("updating {} data", merchant.getUsername());
    String admin = authenticate.getName();
    MerchantResponseDTO response = merchantLogic.updateData(admin, merchant);
        return ResponseEntity.ok(response);
    }
}

