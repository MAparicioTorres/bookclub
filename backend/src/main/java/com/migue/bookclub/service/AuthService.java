package com.migue.bookclub.service;

import com.migue.bookclub.dto.LoginRequest;
import com.migue.bookclub.dto.LoginResponse;
import com.migue.bookclub.dto.RegisterRequest;
import com.migue.bookclub.enums.Role;
import com.migue.bookclub.exception.DuplicateResourceException;
import com.migue.bookclub.exception.InvalidCredentialsException;
import com.migue.bookclub.model.User;
import com.migue.bookclub.repository.UserRepository;
import com.migue.bookclub.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse register(RegisterRequest request) {

        String username = request.getUsername();
        String email = request.getEmail();

        // check if user already exists
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new DuplicateResourceException("Username: " + username + " is already taken");
        }

        //check if email exists
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Email is already in use");
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

        String token = jwtService.generateToken(user.getUsername());

        return new LoginResponse(
                savedUser.getUsername(),
                savedUser.getRole().toString(),
                token
        );
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isEmpty()) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        User user = userOpt.get();
        String username = user.getUsername();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return new LoginResponse(
                username,
                user.getRole().name(),
                jwtService.generateToken(username)
        );
    }
}
