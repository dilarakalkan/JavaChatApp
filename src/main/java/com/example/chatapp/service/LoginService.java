package com.example.chatapp.service;

import com.example.chatapp.model.AuthModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder;

    public LoginService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthModel login(String username, String password) {
        // Sabit (dummy) bir kullanıcı oluştur
        AuthModel dummyUser = new AuthModel();
        dummyUser.setUsername("aa");
        dummyUser.setPassword(passwordEncoder.encode("aa")); // Sabit bir şifre kullanıyoruz

        //Database baglanacak
        // Kullanıcı adı ve şifre sabit verilerle eşleşiyor mu kontrol et
        if ("aa".equals(username) && passwordEncoder.matches(password, dummyUser.getPassword())) {
            return dummyUser;
        }

        // Eşleşme olmazsa null döndür
        return null;
    }
}
