package com.factor.shopOnline.Service;

import com.factor.shopOnline.Component.MerchantDT;
import com.factor.shopOnline.Model.Merchant;
import com.factor.shopOnline.Model.MerchantDTO;
import com.factor.shopOnline.Repository.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional

public class MerchantLogic {

    @Autowired
    private MerchantRepo repo;
    @Autowired
    private MerchantDT transfer;
    @Autowired
    private PasswordEncoder encoder;


    public String addAdmin(MerchantDTO data){
        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()){
            return "Email already exists";
        }
        Merchant admin = transfer.dataTrnsf(data);
        repo.save(admin);
        return "New Admin registered as: " + data.getUsername();
    }
    
    public UserDetails loadUserByUsername(String username){
      Merchant admin = repo.findByUsernameIgnoreCase(username)
              .orElseThrow(()-> new UsernameNotFoundException("User not Found"));

            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(admin.getRoles().name()))

            );
    }

    public String updateData(String user, MerchantDTO key){
        Merchant admin = repo.findByUsernameIgnoreCase(user)
                .orElseThrow(()-> new UsernameNotFoundException("User not Found"));

        boolean updated = false;
        if (key.getPassword() != null){
            admin.setPassword(encoder.encode(key.getPassword()));
            updated = true;
        }
        if (key.getEmail() != null && !key.getEmail().equals(admin.getEmail())){
            if (repo.findByEmailIgnoreCase(key.getEmail())
                    .filter(u -> !u.getId().equals(admin.getId()))
                    .isPresent()){
                return "Email already exists";
            }
            admin.setEmail(key.getEmail());
            updated = true;
        }
        if(key.getPhone() != null && !key.getPhone().equals(admin.getPhone())){
            if(repo.findByPhone(key.getPhone())
                    .filter(u -> !u.getId().equals(admin.getId()))
                    .isPresent()){
                return "Phone number already exists";
            }
            admin.setPhone(key.getPhone());
            updated = true;
        }
        if(!updated){
            return "No change made";
        }
        repo.save(admin);
        return "Change was made successfully";
    }
}