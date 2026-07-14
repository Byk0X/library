package com.example.library.controller;

import com.example.library.dto.BookListResponseDto;
import com.example.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }


    @GetMapping("/listBooks")
    public ResponseEntity<Page<BookListResponseDto>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            Pageable pageable
    ) {

        Page<BookListResponseDto> books = bookService.getBooks(title, author, pageable);

        return ResponseEntity.ok(books);

    }


}
