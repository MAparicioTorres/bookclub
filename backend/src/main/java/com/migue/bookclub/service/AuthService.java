package com.migue.bookclub.service;

import com.migue.bookclub.dto.LoginResponse;
import com.migue.bookclub.dto.RegisterRequest;
import com.migue.bookclub.enums.Role;
import com.migue.bookclub.model.User;
import com.migue.bookclub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }

        // hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // create the user with request data
        User user = new User(
                request.getUsername(),
                hashedPassword,
                request.getEmail(),
                Role.USER
        );

        User savedUser = userRepository.save(user);

        return new LoginResponse(
                savedUser.getUsername(),
                savedUser.getRole().toString(),
                "TOKEN_TEST"
        );
    }
}
