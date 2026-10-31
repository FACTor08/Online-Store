package com.factor.shopOnline.merchant;

import com.factor.shopOnline.User.UserData;
import com.factor.shopOnline.User.UserRepo;
import com.factor.shopOnline.exception.DataNotFoundException;
import com.factor.shopOnline.exception.ExistingDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MerchantLogic {

    private final UserRepo repo;
    private final MerchantMapper transfer;
    private final PasswordEncoder encoder;


    public MerchantResponseDTO addAdmin(MerchantDTO data){
        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()){
            throw new ExistingDataException("email already in use");
        }
        UserData admin = transfer.dataTrnsf(data);
        UserData save = repo.save(admin);
        return transfer.response(save);
            }


    public MerchantResponseDTO updateData(String user, MerchantDTO key){
        UserData admin = repo.findByUsernameIgnoreCase(user)
                .orElseThrow(()-> new DataNotFoundException("username not Found"));

        if (key.getPassword() != null){
            admin.setPassword(encoder.encode(key.getPassword()));
        }
        if (key.getEmail() != null && !key.getEmail().equals(admin.getEmail())){
            if (repo.findByEmailIgnoreCase(key.getEmail())
                    .filter(u -> !u.getId().equals(admin.getId()))
                    .isPresent()){
                throw new ExistingDataException("email already in use") ;

            }
            admin.setEmail(key.getEmail());
        }
        if(key.getPhone() != null && !key.getPhone().equals(admin.getPhone())){
            if(repo.findByPhone(key.getPhone())
                    .filter(u -> !u.getId().equals(admin.getId()))
                    .isPresent()){
                throw new ExistingDataException("phone number already in use") ;
            }
            admin.setPhone(key.getPhone());
        }
        UserData data = repo.save(admin);
return transfer.response(data);
    }
}