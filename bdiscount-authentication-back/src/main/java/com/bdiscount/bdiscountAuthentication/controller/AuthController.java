package com.bdiscount.bdiscountAuthentication.controller;

import com.bdiscount.bdiscountAuthentication.dto.AuthenticationResponse;
import com.bdiscount.bdiscountAuthentication.dto.LoginRequest;
import com.bdiscount.bdiscountAuthentication.dto.RegisterRequest;
import com.bdiscount.bdiscountAuthentication.dto.TokenRequest;
import com.bdiscount.bdiscountAuthentication.user.dto.UserDto;
import com.bdiscount.bdiscountAuthentication.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService service;

    @Autowired
    public AuthController(
            final UserService service
    ) {
        this.service = service;
    }

    /**
     * Inscription
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            final UserDto userCreated = service.register(request);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .user(userCreated)
                    .isValid(true)
                    .message("User created")
                    .token(null)
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(401).body(AuthenticationResponse.builder()
                    .isValid(false)
                    .message("Error : " + e.getMessage())
                    .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ) {
        try {
            final UserDto userCreated = service.login(request);
            if (userCreated == null) throw new IllegalArgumentException("unable to connect this user");
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .user(userCreated)
                    .isValid(true)
                    .message("User login")
                    .token(userCreated.getToken())
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(401).body(AuthenticationResponse.builder()
                    .isValid(false)
                    .message("Error : " + e.getMessage())
                    .build());
        }
    }


    @PostMapping("check")
    public ResponseEntity<AuthenticationResponse> checkToken(
            @RequestBody TokenRequest request
    ) {
        try {
            final UserDto userChecked = service.checkToken(request);
            if (userChecked == null) throw new IllegalArgumentException("unable to find this token");
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .user(userChecked)
                    .isValid(true)
                    .message("Token Verified !")
                    .token(userChecked.getToken())
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(401).body(AuthenticationResponse.builder()
                    .isValid(false)
                    .message("Error : " + e.getMessage())
                    .build());
        }
    }

}
