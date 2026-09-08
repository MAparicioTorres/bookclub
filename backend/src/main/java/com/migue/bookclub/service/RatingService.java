package com.migue.bookclub.service;

import com.migue.bookclub.dto.CreateRatingRequest;
import com.migue.bookclub.dto.RatingResponse;
import com.migue.bookclub.dto.UpdateRatingRequest;
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

    public RatingResponse rateBook(User user, CreateRatingRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (ratingRepository.existsByUserIdAndBookId(user.getId(), request.getBookId())) {
            throw new DuplicateResourceException("You have already rated " + "'" + book.getTitle() + "'");
        }

        UserBookRating rating = new UserBookRating(user, book, request.getRating());
        ratingRepository.save(rating);

        return new RatingResponse(user.getUsername(), book.getTitle(), request.getRating());
    }

    public RatingResponse updateRating(User user, long bookId, UpdateRatingRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        UserBookRating rating = ratingRepository.findByUserIdAndBookId(user.getId(), book.getId()).orElseThrow(() -> new ResourceNotFoundException("You haven't yet rated " + "'" + book.getTitle() + "'"));

        rating.setRating(request.getRating());

        ratingRepository.save(rating);

        return new RatingResponse(
                user.getUsername(),
                book.getTitle(),
                rating.getRating()
        );

    }
}
