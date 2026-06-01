package com.migue.bookclub.repository;

import com.migue.bookclub.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
            SELECT COUNT(b) > 0 FROM Book b
            WHERE REPLACE(LOWER(b.title), ' ', '') = REPLACE(LOWER(:title), ' ', '')
            """)
    boolean existsByTitleIgnoringSpaces(@Param("title") String title);
}
