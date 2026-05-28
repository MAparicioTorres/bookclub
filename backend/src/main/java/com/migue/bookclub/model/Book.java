package com.migue.bookclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable = false)
    private String title;

    private LocalDate publishedDate;

    private LocalDate startDate;

    private LocalDate finishDate;

    private String coverUrl;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @Transient
    private BigDecimal avgRating;

    public Book(String title, LocalDate publishedDate, LocalDate startDate, LocalDate finishDate, String coverUrl) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.coverUrl = coverUrl;
    }
}
