package com.migue.bookclub.repository;

import com.migue.bookclub.model.UserBookRating;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<UserBookRating, Long> {

    @EntityGraph(attributePaths = "user")
    List<UserBookRating> findByBookId(@Param("bookId") Long bookId);

    boolean existsByUserIdAndBookId(long userId, long bookId);

    Optional<UserBookRating> findByUserIdAndBookId(long userId, long bookId);
}
