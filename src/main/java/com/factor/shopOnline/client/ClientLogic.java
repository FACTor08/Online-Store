package com.factor.shopOnline.client;

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
public class ClientLogic {
    private final UserRepo repo;
    private final ClientMapper transfer;
    private final PasswordEncoder encoder;

    public ClientResponseDTO clientSignUp(ClientDTO data) {
        if(data.getEmail().isEmpty() || data.getPassword().isEmpty()){
            throw new IllegalArgumentException("invalid email or password");
        }
        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email is already in use");        }
        else if (repo.findByPhone(data.getPhone()).isPresent()) {
            throw new IllegalArgumentException("phone is already in use");
        }
        else {
        UserData user = transfer.dataTransfer(data);
            UserData newClient = repo.save(user);
      return  transfer.response(newClient);
        }
    }


    public ClientResponseDTO updateData(String user, ClientDTO data) {
        UserData key = repo.findByUsername(user)
                 .orElseThrow(() -> new DataNotFoundException("username not found... \n" +
                         " note: #casesensitive"));



        if (data.getPassword() != null) {
            key.setPassword(encoder.encode(data.getPassword()));

        }

        if (data.getPhone() != null && !data.getPhone().equals(key.getPhone())) {
            if (repo.findByPhone(data.getPhone())
                    .filter(u -> !u.getId().equals(key.getId()))
                    .isPresent()){
                throw new ExistingDataException("phone number already exists");
            }
            key.setPhone(data.getPhone());
        }
        if (data.getEmail() != null && !data.getEmail().equals(key.getEmail())) {
            if (repo.findByEmailIgnoreCase(data.getEmail())
                    .filter(u -> !u.getId().equals(key.getId()))
                    .isPresent()){
                throw new ExistingDataException("email already exists");
            }
                key.setEmail(data.getEmail());

        }
        UserData updated = repo.save(key);
        return transfer.response(updated);

  }

    public ClientResponseDTO updateUsername(String user, ClientDTO data){
        UserData client = repo.findByEmailIgnoreCase(user)
                .orElseThrow(()-> new DataNotFoundException("email not found"));
        if (repo.findByUsername(data.getUsername())
                .filter(u -> !u.getId().equals(client.getId()))
                .isPresent()){
            throw new ExistingDataException("username already exists");
        }
        if(data.getUsername() != null && !data.getUsername().equals(client.getUsername())){
            client.setUsername(data.getUsername());
        repo.save(client);
            }else {
            throw new ExistingDataException("username already exists");
        }
            return transfer.response(client);
        }

}