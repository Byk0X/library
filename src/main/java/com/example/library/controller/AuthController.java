package com.example.library.controller;

import com.example.library.dto.LoginRequestDto;
import com.example.library.dto.LoginResponseDto;
import com.example.library.dto.RegisterRequestDto;
import com.example.library.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        try {
            authService.register(registerRequestDto);

            return ResponseEntity.ok("Rejestracja pomyślna");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Rejestracja nieudana");

        }

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {


        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return ResponseEntity.ok(loginResponseDto);
    }


}
