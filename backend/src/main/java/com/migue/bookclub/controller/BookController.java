package com.migue.bookclub.controller;

import com.migue.bookclub.dto.BookDetailResponse;
import com.migue.bookclub.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDetailResponse getBookDetails(@PathVariable long id) {
        return bookService.getBookDetails(id);
    }
}
