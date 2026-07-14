package com.example.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
        @NotBlank(message = "Email nie może być pusty")
        @Email(message = "Format adresu email jest niepoprawny")
        String email,
        @NotBlank(message = "Hasło nie może być puste")
        String password,

        @NotBlank(message = "Imie nie może puste")
        String firstName,

        @NotBlank(message = "Naziwsko nie może być puste")
        String lastName
) {
}
