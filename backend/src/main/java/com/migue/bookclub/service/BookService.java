package com.migue.bookclub.service;

import com.migue.bookclub.dto.BookDetailResponse;
import com.migue.bookclub.dto.BookResponse;
import com.migue.bookclub.dto.CreateBookRequest;
import com.migue.bookclub.dto.RatingResponse;
import com.migue.bookclub.exception.DuplicateResourceException;
import com.migue.bookclub.exception.ResourceNotFoundException;
import com.migue.bookclub.model.Author;
import com.migue.bookclub.model.Book;
import com.migue.bookclub.model.Genre;
import com.migue.bookclub.model.UserBookRating;
import com.migue.bookclub.repository.AuthorRepository;
import com.migue.bookclub.repository.BookRepository;
import com.migue.bookclub.repository.GenreRepository;
import com.migue.bookclub.repository.RatingRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final RatingRepository ratingRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookDetailResponse getBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
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

    public BookResponse createBook(CreateBookRequest request) {
        // check for duplicate (ignoring case and spaces)
        String title = request.getTitle();
        if (bookRepository.existsByTitleIgnoringSpaces(title)) {
            throw new DuplicateResourceException("'" + title + "'" + " already exists");
        }

        // create the Book entity
        Book book = new Book(
                title,
                request.getPublishedDate(),
                request.getStartDate(),
                request.getFinishDate(),
                request.getCoverUrl()
        );

        // look up or create authors
        List<Author> authors = request.getAuthors().stream()
                .map(String::trim)
                .map(name -> authorRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> authorRepository.save(new Author(name)))
                ).toList();
        book.setAuthors(authors);

        // look up or create genres
        List<Genre> genres = request.getGenres().stream()
                .map(String::trim)
                .map(name -> genreRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> genreRepository.save(new Genre(name)))
                ).toList();

        book.setAuthors(authors);
        book.setGenres(genres);

        bookRepository.save(book);

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthors().stream().map(Author::getName).toList(),
                book.getGenres().stream().map(Genre::getName).toList(),
                book.getCoverUrl()
        );
    }
}
