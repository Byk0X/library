package com.example.library.dto;

public record BookListResponseDto(
        Long id,
        String title,
        String author,
        Long totalCopies,
        Long availableCopies

) {
}
