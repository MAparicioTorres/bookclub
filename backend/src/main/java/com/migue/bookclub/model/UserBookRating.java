package com.migue.bookclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "user_book_rating")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserBookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal rating;

    public UserBookRating(User user, Book book, BigDecimal rating) {
        this.user = user;
        this.book = book;
        this.rating = rating;
    }
}
