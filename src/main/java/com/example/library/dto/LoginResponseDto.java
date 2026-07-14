package com.example.library.dto;

import java.util.List;

public record LoginResponseDto(
        String token,
        String email,
        List<String> roles) {
}
