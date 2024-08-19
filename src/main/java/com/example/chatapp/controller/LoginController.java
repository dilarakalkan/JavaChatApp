package com.example.chatapp.controller;

import com.example.chatapp.dto.LoginRequest;
import com.example.chatapp.model.AuthModel;
import com.example.chatapp.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;



@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    //@CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // AuthService kullanarak login işlemini gerçekleştirir
        AuthModel user = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
         String SECRET_KEY = "MLaRy96a9qm47O8Xhqd08wdyoNQoJ3oNrK+pRxkEcxo="; // Güvenli bir anahtar kullanın

                String token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 saat geçerli
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                        .compact();


            return ResponseEntity.ok().body(token);
        } else {
            // Login başarısız ise hata mesajı döndürür
            return ResponseEntity.status(401).body("Kullanıcı adı veya şifre hatalı!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> login2(@RequestBody LoginRequest loginRequest) {
        // AuthService kullanarak login işlemini gerçekleştirir
        AuthModel user = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            String secretKey = "key8586"; // Bu key güvenli bir şekilde saklanmalıdır, genelde application.properties dosyasında tutulur.
            String token = Jwts.builder()
                    .setSubject(user.getUsername()) // Kullanıcı adı gibi bilgiler token'a eklenir
                    .setIssuedAt(new Date()) // Token oluşturulma zamanı
                    .setExpiration(new Date(System.currentTimeMillis() + 864_000_00)) // 1 gün geçerlilik süresi
                    .signWith(SignatureAlgorithm.HS512, secretKey) // HMAC SHA512 algoritması ile imzalanır
                    .compact(); // Token oluşturulur

            return ResponseEntity.ok().body(token);
        } else {
            // Login başarısız ise hata mesajı döndürür
            return ResponseEntity.status(401).body("Kullanıcı adı veya şifre hatalı!");
        }
    }
}


