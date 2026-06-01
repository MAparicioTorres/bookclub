package com.migue.bookclub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDetailResponse {

    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;
    private String coverUrl;

    private List<String> authors;
    private List<String> genres;
    private BigDecimal avgRating;

    private List<RatingResponse> ratings;
}
