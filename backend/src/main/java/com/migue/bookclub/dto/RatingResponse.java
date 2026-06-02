package com.migue.bookclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RatingResponse {
    private String username;
    private String title;
    private BigDecimal rating;
}
