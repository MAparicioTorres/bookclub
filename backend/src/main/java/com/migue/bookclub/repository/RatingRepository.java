package com.migue.bookclub.repository;

import com.migue.bookclub.model.UserBookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Long, UserBookRating> {

}
