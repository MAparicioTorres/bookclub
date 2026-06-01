package com.migue.bookclub.dto;

import com.migue.bookclub.model.Author;
import com.migue.bookclub.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookResponse {
    private long id;
    private String title;
    private List<String> authors;
    private List<String> genres;
    private String coverUrl;
}

