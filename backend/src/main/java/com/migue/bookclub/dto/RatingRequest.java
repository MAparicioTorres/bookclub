package com.migue.bookclub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RatingRequest{
    @NotNull(message = "Book ID is required")
    private long bookId;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating cannot be lower than 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot be greater than 10.0")
    @Digits(integer = 2, fraction = 2, message = "Rating cannot have more than two integers or decimals")
    private BigDecimal rating;
}
