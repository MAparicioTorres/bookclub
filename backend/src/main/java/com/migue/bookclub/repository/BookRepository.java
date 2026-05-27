package com.migue.bookclub.repository;

import com.migue.bookclub.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Long, Book> {
}
