package com.migue.bookclub.service;

import com.migue.bookclub.dto.BookDetailResponse;
import com.migue.bookclub.dto.RatingResponse;
import com.migue.bookclub.exception.ResourceNotFoundException;
import com.migue.bookclub.model.Author;
import com.migue.bookclub.model.Book;
import com.migue.bookclub.model.Genre;
import com.migue.bookclub.model.UserBookRating;
import com.migue.bookclub.repository.BookRepository;
import com.migue.bookclub.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final RatingRepository ratingRepository;

    public BookDetailResponse getBookDetails (long id){
        Book book =  bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        List<UserBookRating> ratings = ratingRepository.findByBookId(id);
        List<RatingResponse> ratingResponses = ratings.stream().map(rating -> new RatingResponse(
                rating.getUser().getUsername(),
                rating.getRating()
        )).toList();

        BigDecimal avgRating = ratings.isEmpty()
                ? null
                : ratings.stream()
                  .map(UserBookRating::getRating)
                  .reduce(BigDecimal.ZERO, BigDecimal::add)
                  .divide(BigDecimal.valueOf(ratings.size()), 2, RoundingMode.HALF_UP);

        return new BookDetailResponse(
                book.getTitle(),
                book.getPublishedDate(),
                book.getStartDate(),
                book.getFinishDate(),
                book.getCoverUrl(),
                book.getAuthors().stream().map(Author::getName).toList(),
                book.getGenres().stream().map(Genre::getName).toList(),
                avgRating,
                ratingResponses
        );
    }
}
