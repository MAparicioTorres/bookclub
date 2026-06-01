package com.migue.bookclub.controller;

import com.migue.bookclub.dto.BookDetailResponse;
import com.migue.bookclub.dto.BookResponse;
import com.migue.bookclub.dto.CreateBookRequest;
import com.migue.bookclub.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDetailResponse getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody @Valid CreateBookRequest request){
        return bookService.createBook(request);
    }
}
