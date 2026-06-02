package com.migue.bookclub.service;

import com.migue.bookclub.dto.RatingRequest;
import com.migue.bookclub.dto.RatingResponse;
import com.migue.bookclub.exception.DuplicateResourceException;
import com.migue.bookclub.exception.ResourceNotFoundException;
import com.migue.bookclub.model.Book;
import com.migue.bookclub.model.User;
import com.migue.bookclub.model.UserBookRating;
import com.migue.bookclub.repository.BookRepository;
import com.migue.bookclub.repository.RatingRepository;
import com.migue.bookclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public RatingResponse rateBook(User user, RatingRequest request) {
        // check if book exists
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // check if rating already exists
        if (ratingRepository.existsByUserIdAndBookId(user.getId(), request.getBookId())) {
            throw new DuplicateResourceException("You have already rated " + "'" + book.getTitle() + "'");
        }

        // create and save rating
        UserBookRating rating = new UserBookRating(user, book, request.getRating());
        ratingRepository.save(rating);

        // return RatingResponse
        return new RatingResponse(user.getUsername(), book.getTitle(), request.getRating());
    }
}
