package com.factor.shopOnline.Service;

import com.factor.shopOnline.Component.ClientDT;
import com.factor.shopOnline.Model.Client;
import com.factor.shopOnline.Model.ClientDTO;
import com.factor.shopOnline.Repository.ClientRepo;
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
public class ClientLogic {

    @Autowired
    private ClientRepo repo;

    @Autowired
    private ClientDT transfer;

    @Autowired
    private PasswordEncoder encoder;

    public String clientSignUp(ClientDTO data) {

        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()) {
            return "Email already exists";
        }
        else if (repo.findByUsernameIgnoreCase(data.getUsername()).isPresent()) {
            return "Username already exists";
        }
        else if (repo.findByPhone(data.getPhone()).isPresent()) {
            return "Phone number already exists";
        }
        else {
        Client user = transfer.dataTransfer(data);
        repo.save(user);
        return "Sign up Successful";
        }
    }

    public UserDetails loadUserByUsername(String username) {
        Client user = repo.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRoles().name()))
        );
    }

    public String updateData(String user, ClientDTO data) {
        Client key = repo.findByUsernameIgnoreCase(user)
                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean update = false;

        if (data.getPassword() != null) {
            key.setPassword(encoder.encode(data.getPassword()));
            update = true;
        }

        if (data.getPhone() != null && !data.getPhone().equals(key.getPhone())) {
            if (repo.findByPhone(data.getPhone())
                    .filter(u -> !u.getId().equals(key.getId()))
                    .isPresent()){
                return "Phone number already exists";
            }
            key.setPhone(data.getPhone());
            update = true;
        }
        if (data.getEmail() != null && !data.getEmail().equals(key.getEmail())) {
            if (repo.findByEmailIgnoreCase(data.getEmail())
                    .filter(u -> !u.getId().equals(key.getId()))
                    .isPresent()){
                return "Email already exists";
            }
                key.setEmail(data.getEmail());
                update = true;
        }

    if (!update){
        return "No change made";
    }
    repo.save(key);
    return "Change was made successfully";
    }

    public String updateUsername(String user, ClientDTO data){
        Client client = repo.findByEmailIgnoreCase(user).orElseThrow(()-> new UsernameNotFoundException("Email not found"));
        if (repo.findByUsernameIgnoreCase(data.getUsername())
                .filter(u -> !u.getId().equals(client.getId()))
                .isPresent()){
            return "Username already exists";
        }
        if(data.getUsername() != null && !data.getUsername().equals(client.getUsername())){
            client.setUsername(data.getUsername());
        repo.save(client);
        return "Username Change Successful";
            }
        return  "No change made";
        }
}