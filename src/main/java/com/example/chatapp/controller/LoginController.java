package com.example.chatapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/auth")
public class LoginController {

    //private final AuthenticationManager authenticationManager;

    /*public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }*/

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticationRequest =
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            /*Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);*/

            // If authentication is successful, return 200 OK
            return ResponseEntity.ok().build();
        } catch (AuthenticationException ex) {
            // If authentication fails, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
record LoginRequest(String username, String password) {
}


