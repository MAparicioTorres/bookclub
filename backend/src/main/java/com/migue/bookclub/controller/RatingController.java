package com.migue.bookclub.controller;

import com.migue.bookclub.dto.CreateRatingRequest;
import com.migue.bookclub.dto.RatingResponse;
import com.migue.bookclub.dto.UpdateRatingRequest;
import com.migue.bookclub.model.User;
import com.migue.bookclub.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public RatingResponse rateBook(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateRatingRequest request
    ) {
        return ratingService.rateBook(user, request);
    }

    @PutMapping("/{bookId}")
    public RatingResponse updateRating(
            @AuthenticationPrincipal User user,
            @PathVariable long bookId,
            @RequestBody @Valid UpdateRatingRequest request
    ) {
        return ratingService.updateRating(user, bookId, request);
    }

}
