package com.example.library.service;

import com.example.library.dto.BookListResponseDto;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Page<BookListResponseDto> getBooks(String title, String author, Pageable pageable) {

        String searchTitle = title != null ? title : "";
        String searchAuthor = author != null ? author : "";

        Page<Book> booksPage = bookRepository.findByTitleContainingAndAuthorContaining(searchTitle, searchAuthor, pageable);

        return booksPage.map(book -> new BookListResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        ));


    }


}
