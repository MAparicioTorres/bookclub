package com.migue.bookclub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "user_book_rating")
@Getter
@NoArgsConstructor
@ToString
public class UserBookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Setter
    @Column(precision = 4, scale = 2)
    private BigDecimal rating;

    public UserBookRating(User user, Book book, BigDecimal rating) {
        this.user = user;
        this.book = book;
        this.rating = rating;
    }
}
