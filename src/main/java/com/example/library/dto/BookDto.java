package com.example.library.dto;

import com.example.library.entity.Author;

import java.util.Date;

public record BookDto(
        String title,
        Author author,
        String isbn,
        String publisher,
        Date publicationDate,
        String description,
        Integer pages,
        Long totalCopies,
        Long availableCopies,
        String coverUrl
) {

}