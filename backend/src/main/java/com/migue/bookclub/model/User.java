package com.migue.bookclub.model;

import com.migue.bookclub.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member") // 'user' conflicts with PostgreSQL as it's a reserved word
@Getter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role = Role.USER; // default new users to USER role

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
