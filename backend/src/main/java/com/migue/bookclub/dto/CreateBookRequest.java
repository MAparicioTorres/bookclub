package com.migue.bookclub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateBookRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;
    private String coverUrl;
    private List<@NotBlank(message = "Genre name cannot be blank") String> genres;
    @NotEmpty(message = "At least one author is required")
    private List<@NotBlank(message = "Author name cannot be blank") String> authors;
}
