package com.migue.bookclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String title;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> authors;

    @Transient
    @Setter
    private BigDecimal avgRating;

    public Book(String title) {
        this.title = title;
    }
}
